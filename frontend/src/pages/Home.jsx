import { Link } from 'react-router-dom';

export default function Home() {
    return (
        <div className="min-h-screen bg-slate-50">
            <div className="bg-primary-700 py-20 lg:py-32">
                <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8 text-center">
                    <h1 className="text-4xl lg:text-6xl font-extrabold text-white tracking-tight mb-6">
                        Find Your Dream Home Today
                    </h1>
                    <p className="text-xl text-primary-100 max-w-2xl mx-auto mb-10">
                        Discover the best real estate properties curated by top agents, directly on the EstateHub microservices platform.
                    </p>
                    <div className="flex justify-center space-x-4">
                        <Link to="/properties" className="bg-white text-primary-700 hover:bg-slate-100 font-semibold py-3 px-8 rounded-lg shadow-lg transition-all">
                            Browse Properties
                        </Link>
                        <Link to="/agents" className="bg-primary-600 bg-opacity-30 border border-primary-400 text-white hover:bg-opacity-40 font-semibold py-3 px-8 rounded-lg shadow-lg transition-all">
                            Find an Agent
                        </Link>
                    </div>
                </div>
            </div>

            <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8 py-16">
                <div className="text-center mb-12">
                    <h2 className="text-3xl font-bold text-slate-800">Featured Features</h2>
                    <p className="mt-4 text-slate-600">Built on a scalable, gateway-driven architecture.</p>
                </div>
                <div className="grid grid-cols-1 md:grid-cols-3 gap-8">
                    <div className="card p-6 text-center">
                        <div className="w-12 h-12 bg-primary-100 text-primary-600 rounded-full flex items-center justify-center mx-auto mb-4 text-xl font-bold">1</div>
                        <h3 className="font-semibold text-lg text-slate-800 mb-2">Microservices</h3>
                        <p className="text-slate-600 text-sm">Separated domains for properties, agents, booking, and payments.</p>
                    </div>
                    <div className="card p-6 text-center">
                        <div className="w-12 h-12 bg-primary-100 text-primary-600 rounded-full flex items-center justify-center mx-auto mb-4 text-xl font-bold">2</div>
                        <h3 className="font-semibold text-lg text-slate-800 mb-2">Secure API Gateway</h3>
                        <p className="text-slate-600 text-sm">All requests routed through our Spring Cloud Gateway on port 8080.</p>
                    </div>
                    <div className="card p-6 text-center">
                        <div className="w-12 h-12 bg-primary-100 text-primary-600 rounded-full flex items-center justify-center mx-auto mb-4 text-xl font-bold">3</div>
                        <h3 className="font-semibold text-lg text-slate-800 mb-2">Modern UI</h3>
                        <p className="text-slate-600 text-sm">Fast, responsive frontend built with Vite, React 18, and Tailwind CSS.</p>
                    </div>
                </div>
            </div>
        </div>
    );
}
