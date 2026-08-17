export default function Footer() {
    return (
        <footer className="bg-dark py-12">
            <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8 text-center">
                <p className="text-slate-400 text-sm">
                    &copy; {new Date().getFullYear()} EstateHub Platform. All rights reserved. Built with microservices.
                </p>
            </div>
        </footer>
    );
}
