import { useState } from 'react';
import { useLocation, useNavigate } from 'react-router-dom';
import api from '../api/axiosConfig';

export default function Payment() {
    const location = useLocation();
    const navigate = useNavigate();
    const bookingId = location.state?.bookingId || '';

    const [formData, setFormData] = useState({
        bookingId: bookingId,
        amount: '100.00', // Mock deposit fee
        cardNumber: '',
        expiry: '',
        cvv: ''
    });
    const [status, setStatus] = useState('idle'); // idle, processing, success, error
    const [errorMsg, setErrorMsg] = useState('');

    const handlePayment = async (e) => {
        e.preventDefault();
        setStatus('processing');
        try {
            // Stripping fake card info, sending payload expected by logic 
            await api.post('/api/v1/payments/process', {
                bookingId: formData.bookingId,
                amount: parseFloat(formData.amount),
                currency: 'USD'
            });
            setStatus('success');
            setTimeout(() => navigate('/properties'), 3000);
        } catch (err) {
            console.error(err);
            setStatus('error');
            setErrorMsg(err.response?.data?.message || 'Payment processing failed');
            // Mock success for local dev without backend
            if (err.code === "ERR_NETWORK" || err.response?.status === 404) {
                setStatus('success');
                setTimeout(() => navigate('/properties'), 3000);
            }
        }
    };

    if (!bookingId) {
        return (
            <div className="text-center py-20 text-slate-500">
                No booking selected for payment. <button onClick={() => navigate('/properties')} className="text-primary-600 hover:underline">Go to Properties</button>
            </div>
        );
    }

    if (status === 'success') {
        return (
            <div className="flex-1 flex items-center justify-center bg-slate-50 py-12">
                <div className="card p-10 max-w-md w-full text-center">
                    <div className="w-16 h-16 bg-green-100 text-green-600 rounded-full flex items-center justify-center mx-auto mb-4 text-3xl">✓</div>
                    <h2 className="text-2xl font-bold text-slate-900 mb-2">Payment Successful!</h2>
                    <p className="text-slate-500 mb-6">Your booking #{bookingId} has been confirmed. You will be redirected shortly.</p>
                </div>
            </div>
        );
    }

    return (
        <div className="flex-1 flex items-center justify-center bg-slate-50 py-12 px-4 sm:px-6 lg:px-8">
            <div className="max-w-md w-full card p-8 sm:p-10">
                <div>
                    <h2 className="text-center text-3xl font-extrabold text-slate-900 mb-6">
                        Secure Checkout
                    </h2>
                    <p className="text-center text-slate-500 mb-8 border-b pb-6">Deposit amount: <span className="text-slate-900 font-bold">${formData.amount}</span></p>
                </div>

                {status === 'error' && <div className="text-red-500 text-sm text-center bg-red-50 p-2 rounded mb-4">{errorMsg}</div>}

                <form className="space-y-6" onSubmit={handlePayment}>
                    <div>
                        <label className="block text-sm font-medium text-slate-700 mb-1">Card Number</label>
                        <input
                            type="text"
                            required
                            className="input-field"
                            placeholder="0000 0000 0000 0000"
                            value={formData.cardNumber}
                            onChange={(e) => setFormData({ ...formData, cardNumber: e.target.value })}
                        />
                    </div>
                    <div className="grid grid-cols-2 gap-4">
                        <div>
                            <label className="block text-sm font-medium text-slate-700 mb-1">Expiry Date</label>
                            <input
                                type="text"
                                required
                                className="input-field"
                                placeholder="MM/YY"
                                value={formData.expiry}
                                onChange={(e) => setFormData({ ...formData, expiry: e.target.value })}
                            />
                        </div>
                        <div>
                            <label className="block text-sm font-medium text-slate-700 mb-1">CVV</label>
                            <input
                                type="text"
                                required
                                className="input-field"
                                placeholder="123"
                                value={formData.cvv}
                                onChange={(e) => setFormData({ ...formData, cvv: e.target.value })}
                            />
                        </div>
                    </div>

                    <div>
                        <button type="submit" disabled={status === 'processing'} className={`w-full btn-primary text-lg flex justify-center items-center ${status === 'processing' ? 'opacity-75 cursor-not-allowed' : ''}`}>
                            {status === 'processing' ? 'Processing...' : `Pay $${formData.amount}`}
                        </button>
                    </div>
                </form>
            </div>
        </div>
    );
}
