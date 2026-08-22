import { useState, useEffect } from 'react';
import api from '../api/axiosConfig';

export default function Agents() {
    const [agents, setAgents] = useState([]);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);

    useEffect(() => {
        const fetchAgents = async () => {
            try {
                const response = await api.get('/api/v1/agents');
                const data = response.data;
                const agentList = Array.isArray(data) ? data : data?.content || data?.data || [];
                setAgents(agentList);
            } catch (err) {
                setError('Failed to load agents.');
            } finally {
                setLoading(false);
            }
        };
        fetchAgents();
    }, []);

    if (loading) return <div className="text-center py-20 text-slate-500 font-medium">Loading agents...</div>;

    return (
        <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8 py-12">
            <h1 className="text-3xl font-extrabold text-slate-800 mb-2">Our Trusted Agents</h1>
            <p className="text-slate-500 mb-8">Connect with one of our certified real estate professionals today.</p>

            {error && <div className="bg-red-50 text-red-500 p-4 rounded-lg mb-8">{error}</div>}

            <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-4 gap-8">
                {agents.length === 0 && !error ? (
                    <p className="text-slate-500 col-span-4 text-center py-10">No agents found.</p>
                ) : (
                    agents.map(agent => (
                        <div key={agent.id} className="card p-6 text-center shadow hover:shadow-md transition">
                            <div className="w-24 h-24 mx-auto bg-primary-100 text-primary-600 rounded-full flex items-center justify-center text-3xl font-bold mb-4">
                                {agent.firstName ? agent.firstName[0] : 'A'}
                            </div>
                            <h3 className="text-xl font-bold text-slate-900 mb-1">{agent.firstName} {agent.lastName}</h3>
                            <p className="text-primary-600 font-medium text-sm mb-4">{agent.specialty || 'Real Estate Agent'}</p>

                            <div className="text-slate-500 text-sm">
                                <p>Phone: {agent.phoneNumber}</p>
                                <p>Rating: {agent.rating || 'N/A'}/5</p>
                            </div>
                        </div>
                    ))
                )}
            </div>
        </div>
    );
}
