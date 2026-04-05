export default function NotFound() {
    return (
        <div className="min-vh-100 bg-light d-flex flex-column align-items-center justify-content-center text-center px-3">
            <h1 className="display-1 fw-bold text-primary">404</h1>
            <h2 className="h4 fw-semibold text-dark mb-2">Page Not Found</h2>
            <p className="text-muted mb-4">
                The page you're looking for doesn't exist or has been moved.
            </p>
            <a href="/" className="btn btn-primary px-4">
                Go Home
            </a>
        </div>
    );
}
