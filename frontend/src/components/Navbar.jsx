import { useContext } from 'react';
import { Link, useNavigate } from 'react-router-dom';
import { AuthContext } from '../context/AuthContext';

export default function Navbar() {
    const { user, logout } = useContext(AuthContext);
    const navigate = useNavigate();

    const handleLogout = () => {
        logout();
        navigate('/');
    };

    return (
        <nav className="bg-white border-b border-slate-200">
            <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
                <div className="flex justify-between h-16">
                    <div className="flex items-center">
                        <Link to="/" className="flex-shrink-0 flex items-center gap-2">
                            <div className="w-8 h-8 bg-primary-600 rounded-lg flex items-center justify-center">
                                <span className="text-white font-bold text-xl">E</span>
                            </div>
                            <span className="font-bold text-xl text-slate-800 tracking-tight">EstateHub</span>
                        </Link>
                        <div className="hidden sm:ml-8 sm:flex sm:space-x-8">
                            <Link to="/properties" className="text-slate-600 hover:text-primary-600 px-3 py-2 text-sm font-medium transition-colors">Properties</Link>
                            <Link to="/agents" className="text-slate-600 hover:text-primary-600 px-3 py-2 text-sm font-medium transition-colors">Agents</Link>
                        </div>
                    </div>
                    <div className="flex items-center space-x-4">
                        {user ? (
                            <button onClick={handleLogout} className="btn-secondary text-sm">
                                Logout
                            </button>
                        ) : (
                            <>
                                <Link to="/login" className="text-slate-600 hover:text-slate-900 px-3 py-2 text-sm font-medium">Login</Link>
                                <Link to="/register" className="btn-primary text-sm">Sign Up</Link>
                            </>
                        )}
                    </div>
                </div>
            </div>
        </nav>
    );
}
