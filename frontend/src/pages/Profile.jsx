import { useEffect, useState } from "react";
import { getProfile } from "../services/customerService";
import {
  Button,
  Card,
  Col,
  Container,
  Form,
  Row,
  Spinner,
} from "react-bootstrap";

function Profile() {
  const [user, setUser] = useState(null);
  const [loading, setLoading] = useState(true);

  const [cars, setCars] = useState([]);
  const [carForm, setCarForm] = useState({
    make: "",
    model: "",
    registration: "",
    color: "",
  });
  const [editingCarId, setEditingCarId] = useState(null);

  useEffect(() => {
    async function fetchProfile() {
      try {
        const profileData = await getProfile();

        if (profileData) {
          setUser(profileData);
          setCars(profileData.cars || []);
        } else {
          setUser({
            name: "",
            surname: "",
            email: "",
            address: "",
            isCorporate: false,
          });
        }
      } catch (error) {
        console.error("Error fetching profile:", error);
        setUser({
          name: "",
          surname: "",
          email: "",
          address: "",
          isCorporate: false,
        });
      } finally {
        setLoading(false);
      }
    }

    fetchProfile();
  }, []);

  const handleCarChange = (e) => {
    const { name, value } = e.target;
    setCarForm((prev) => ({
      ...prev,
      [name]: value,
    }));
  };

  const resetCarForm = () => {
    setCarForm({
      make: "",
      model: "",
      registration: "",
      color: "",
    });
    setEditingCarId(null);
  };

  const handleSubmitCar = (e) => {
    e.preventDefault();

    if (
      !carForm.make.trim() ||
      !carForm.model.trim() ||
      !carForm.registration.trim() ||
      !carForm.color.trim()
    ) {
      return;
    }

    if (editingCarId) {
      setCars((prev) =>
        prev.map((car) =>
          car.id === editingCarId ? { ...car, ...carForm } : car
        )
      );
    } else {
      const newCar = {
        id: Date.now(),
        ...carForm,
      };
      setCars((prev) => [...prev, newCar]);
    }

    resetCarForm();
  };

  const handleEditCar = (car) => {
    setCarForm({
      make: car.make,
      model: car.model,
      registration: car.registration,
      color: car.color,
    });
    setEditingCarId(car.id);
  };

  const handleDeleteCar = (id) => {
    setCars((prev) => prev.filter((car) => car.id !== id));

    if (editingCarId === id) {
      resetCarForm();
    }
  };

  if (loading) {
    return (
      <div className="container mt-4">
        <h1>Profile</h1>
        <Spinner animation="border" role="status">
          <span className="visually-hidden">Loading...</span>
        </Spinner>
      </div>
    );
  }

  return (
    <div className="container mt-4">
      <h1 className="mb-4">Profile</h1>

      <Container>
        <Card className="p-4 mb-4">
          <h3 className="mb-3">User Details</h3>
          <Form>
            <Row className="mb-3">
              <Col>
                <Form.Group>
                  <Form.Label>Name</Form.Label>
                  <Form.Control
                    type="text"
                    value={user?.name || ""}
                    readOnly
                  />
                </Form.Group>
              </Col>
              <Col>
                <Form.Group>
                  <Form.Label>Surname</Form.Label>
                  <Form.Control
                    type="text"
                    value={user?.surname || ""}
                    readOnly
                  />
                </Form.Group>
              </Col>
            </Row>

            <Form.Group className="mb-3">
              <Form.Label>Email</Form.Label>
              <Form.Control
                type="email"
                value={user?.email || ""}
                readOnly
              />
            </Form.Group>

            <Form.Group className="mb-3">
              <Form.Label>Address</Form.Label>
              <Form.Control
                type="text"
                value={user?.address || ""}
                readOnly
              />
            </Form.Group>

            <Form.Group>
              <Form.Check
                type="switch"
                label="Is corporate"
                checked={user?.isCorporate || false}
                readOnly
              />
            </Form.Group>
          </Form>
        </Card>

        <Card className="p-4 mb-4">
          <h3 className="mb-3">
            {editingCarId ? "Edit Car" : "Add New Car"}
          </h3>

          <Form onSubmit={handleSubmitCar}>
            <Row className="mb-3">
              <Col md={6}>
                <Form.Group>
                  <Form.Label>Make</Form.Label>
                  <Form.Control
                    type="text"
                    name="make"
                    value={carForm.make}
                    onChange={handleCarChange}
                    placeholder="Enter make"
                  />
                </Form.Group>
              </Col>

              <Col md={6}>
                <Form.Group>
                  <Form.Label>Model</Form.Label>
                  <Form.Control
                    type="text"
                    name="model"
                    value={carForm.model}
                    onChange={handleCarChange}
                    placeholder="Enter model"
                  />
                </Form.Group>
              </Col>
            </Row>

            <Row className="mb-3">
              <Col md={6}>
                <Form.Group>
                  <Form.Label>Registration</Form.Label>
                  <Form.Control
                    type="text"
                    name="registration"
                    value={carForm.registration}
                    onChange={handleCarChange}
                    placeholder="Enter registration"
                  />
                </Form.Group>
              </Col>

              <Col md={6}>
                <Form.Group>
                  <Form.Label>Color</Form.Label>
                  <Form.Control
                    type="text"
                    name="color"
                    value={carForm.color}
                    onChange={handleCarChange}
                    placeholder="Enter color"
                  />
                </Form.Group>
              </Col>
            </Row>

            <div className="d-flex gap-2">
              <Button type="submit" variant="primary">
                {editingCarId ? "Update Car" : "Add Car"}
              </Button>

              {editingCarId && (
                <Button
                  type="button"
                  variant="secondary"
                  onClick={resetCarForm}
                >
                  Cancel
                </Button>
              )}
            </div>
          </Form>
        </Card>

        <Card className="p-4">
          <h3 className="mb-3">My Cars</h3>

          {cars.length === 0 ? (
            <p>No cars added yet.</p>
          ) : (
            cars.map((car) => (
              <Card key={car.id} className="mb-3 p-3">
                <Row>
                  <Col md={8}>
                    <p className="mb-1">
                      <strong>Make:</strong> {car.make}
                    </p>
                    <p className="mb-1">
                      <strong>Model:</strong> {car.model}
                    </p>
                    <p className="mb-1">
                      <strong>Registration:</strong> {car.registration}
                    </p>
                    <p className="mb-0">
                      <strong>Color:</strong> {car.color}
                    </p>
                  </Col>

                  <Col
                    md={4}
                    className="d-flex gap-2 align-items-center justify-content-md-end mt-3 mt-md-0"
                  >
                    <Button
                      variant="warning"
                      onClick={() => handleEditCar(car)}
                    >
                      Edit
                    </Button>
                    <Button
                      variant="danger"
                      onClick={() => handleDeleteCar(car.id)}
                    >
                      Delete
                    </Button>
                  </Col>
                </Row>
              </Card>
            ))
          )}
        </Card>
      </Container>
    </div>
  );
}

export default Profile;