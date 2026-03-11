import Navbar from "../components/Navbar";
import Footer from "../components/Footer";

function MainLayout({ children }) {
  return (
    <div className="d-flex flex-column min-vh-100">
      <Navbar />

      <main className="flex-fill">
        {children}
      </main>

      <Footer />
    </div>
  );
}

export default MainLayout;
