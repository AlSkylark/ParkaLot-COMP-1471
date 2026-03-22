import { useEffect, useState } from "react";
import { getProfile } from "../services/customerService";
import { Col, Container, Form, Row, Spinner } from "react-bootstrap";

function Profile() {
  const [user, setUser] = useState();
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    async function fetchProfile() {
      const user = await getProfile();
      if (user) {
        console.log(user);
        setUser(user);
        setLoading(false);
      }
    }
    fetchProfile();
  }, []);

  return (
    <div className="container">
      <h1>Profile</h1>
      {loading ? (
        <Spinner animation="border" role="status">
          <span className="visually-hidden">Loading...</span>
        </Spinner>
      ) : (
        <Container>
          <Form>
            <Row className="mb-3">
              <Col>
                <Form.Group>
                  <Form.Label>Name</Form.Label>
                  <Form.Control type="text" value={user.name}></Form.Control>
                </Form.Group>
              </Col>
              <Col>
                <Form.Group>
                  <Form.Label>Surname</Form.Label>
                  <Form.Control type="text" value={user.surname}></Form.Control>
                </Form.Group>
              </Col>
            </Row>
            <Form.Group className="mb-3">
              <Form.Label>Email</Form.Label>
              <Form.Control type="email" value={user.email}></Form.Control>
            </Form.Group>
            <Form.Group className="mb-3">
              <Form.Label>Address</Form.Label>
              <Form.Control type="email" value={user.address}></Form.Control>
            </Form.Group>
            <Form.Group>
              <Form.Check
                type="switch"
                label="Is corporate"
                checked={user.isCorporate}
              />
            </Form.Group>
          </Form>
        </Container>
      )}
    </div>
  );
}

export default Profile;
