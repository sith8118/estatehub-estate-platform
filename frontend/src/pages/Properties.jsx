import { useState, useEffect } from 'react';
import { Link } from 'react-router-dom';
import api from '../api/axiosConfig';

export default function Properties() {
    const [properties, setProperties] = useState([]);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);

    useEffect(() => {
        const fetchProperties = async () => {
            try {
                const response = await api.get('/api/v1/properties');
                setProperties(response.data);
            } catch (err) {
                setError('Failed to load properties. Make sure the API is running.');
            } finally {
                setLoading(false);
            }
        };
        fetchProperties();
    }, []);

    if (loading) return <div className="text-center py-20 text-slate-500 font-medium text-lg">Loading properties...</div>;

    return (
        <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8 py-12">
            <h1 className="text-3xl font-extrabold text-slate-800 mb-2">Available Properties</h1>
            <p className="text-slate-500 mb-8">Browse the best real estate options waiting for you.</p>

            {error && <div className="bg-red-50 text-red-500 p-4 rounded-lg mb-8">{error}</div>}

            <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-8">
                {properties.length === 0 && !error ? (
                    <p className="text-slate-500 col-span-3 text-center py-10">No properties found.</p>
                ) : (
                    properties.map(property => (
                        <div key={property.id} className="card flex flex-col transition hover:-translate-y-1 hover:shadow-md">
                            <div className="h-48 bg-slate-200 w-full object-cover">
                                {/* Placeholder for property image */}
                                <div className="w-full h-full flex items-center justify-center text-slate-400">
                                    Image
                                </div>
                            </div>
                            <div className="p-6 flex-1 flex flex-col">
                                <div className="flex justify-between items-start mb-2">
                                    <h3 className="text-lg font-bold text-slate-900">{property.title || 'Beautiful Home'}</h3>
                                    <span className="text-primary-600 font-bold">${property.price?.toLocaleString()}</span>
                                </div>
                                <p className="text-slate-500 text-sm mb-4 line-clamp-2 flex-1">{property.description || 'Located in a highly sought after neighborhood.'}</p>
                                <Link to={`/properties/${property.id}`} className="btn-secondary w-full text-center">View Details</Link>
                            </div>
                        </div>
                    ))
                )}
            </div>
        </div>
    );
}
