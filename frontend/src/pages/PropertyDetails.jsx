import { useState, useEffect, useContext } from 'react';
import { useParams, useNavigate } from 'react-router-dom';
import api from '../api/axiosConfig';
import { AuthContext } from '../context/AuthContext';

export default function PropertyDetails() {
    const { id } = useParams();
    const [property, setProperty] = useState(null);
    const [loading, setLoading] = useState(true);
    const [bookingLoading, setBookingLoading] = useState(false);
    const { user } = useContext(AuthContext);
    const navigate = useNavigate();

    useEffect(() => {
        const fetchProperty = async () => {
            try {
                const response = await api.get(`/api/v1/properties/${id}`);
                setProperty(response.data);
            } catch (err) {
                console.error(err);
            } finally {
                setLoading(false);
            }
        };
        fetchProperty();
    }, [id]);

    const handleBookVisit = async () => {
        if (!user) {
            alert("Please login to book a visit!");
            navigate('/login');
            return;
        }

        try {
            setBookingLoading(true);
            const response = await api.post('/api/v1/bookings', {
                propertyId: id,
                bookingDate: new Date().toISOString()
            });
            // Redirect to payment with booking ID
            navigate('/payment', { state: { bookingId: response.data.id || Math.floor(Math.random() * 1000) } });
        } catch (err) {
            console.error(err);
            alert('Failed to book visit. Try again.');
            // Mock flow for frontend visualization if backend not ready
            if (err.code === "ERR_NETWORK" || err.response?.status === 404) {
                navigate('/payment', { state: { bookingId: Math.floor(Math.random() * 1000) } });
            }
        } finally {
            setBookingLoading(false);
        }
    };

    if (loading) return <div className="text-center py-20 text-slate-500 font-medium">Loading property details...</div>;
    if (!property) return <div className="text-center py-20 text-red-500 font-medium">Property not found.</div>;

    return (
        <div className="max-w-4xl mx-auto px-4 sm:px-6 lg:px-8 py-12">
            <div className="card overflow-hidden">
                <div className="h-64 sm:h-96 md:h-[400px] bg-slate-200">
                    <div className="w-full h-full flex items-center justify-center text-slate-400 text-lg">
                        Property Image Placeholder
                    </div>
                </div>
                <div className="p-8">
                    <div className="flex justify-between items-start mb-4">
                        <h1 className="text-3xl font-bold text-slate-900">{property.title || 'Luxury Estate'}</h1>
                        <span className="text-2xl text-primary-600 font-bold">${property.price?.toLocaleString() || '1,500,000'}</span>
                    </div>
                    <p className="text-slate-600 mb-8 whitespace-pre-wrap">{property.description || 'Detailed description about the amazing property.'}</p>

                    <div className="grid grid-cols-2 gap-4 border-t border-slate-100 pt-6 mb-8">
                        <div>
                            <p className="text-sm text-slate-500">Location</p>
                            <p className="font-semibold text-slate-800">{property.location || 'Miami, FL'}</p>
                        </div>
                        <div>
                            <p className="text-sm text-slate-500">Status</p>
                            <p className="font-semibold text-slate-800">{property.status || 'AVAILABLE'}</p>
                        </div>
                    </div>

                    <div className="pt-4 flex justify-end">
                        <button onClick={handleBookVisit} disabled={bookingLoading} className={`btn-primary w-full sm:w-auto px-8 ${bookingLoading ? 'opacity-70 cursor-not-allowed' : ''}`}>
                            {bookingLoading ? 'Processing...' : 'Book Visit'}
                        </button>
                    </div>
                </div>
            </div>
        </div>
    );
}
