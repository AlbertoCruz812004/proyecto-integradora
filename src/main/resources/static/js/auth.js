const loginUser = async (credentials) => {
  try {
    const response = await fetch("http://localhost:8080/auth/log-in", {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(credentials),
    });

    if (!response.ok)
      throw new Error("Error en la petición de inicio de sesión");

    return (data = await response.json());
  } catch (error) {
    console.error(error);
  }
};

const handleLoginResponse = (data) => {
  const token = data.jwt;
  if (token) {
    localStorage.setItem("jwt", token);
    return true;
  }
  return false;
};

const redirectToAuthenticatedPage = (isAuthenticated) => {
  if (!isAuthenticated) throw new Error("No fue autenticado");
  window.location.href = "/secure/view/dash";
};

document.getElementById("form").addEventListener("submit", (e) => {
  e.preventDefault();
  const request = {
    username: document.getElementById("in-email").value,
    password: document.getElementById("in-pass").value,
  };

  loginUser(request)
    .then((response) => handleLoginResponse(response))
    .then((status) => redirectToAuthenticatedPage(status))
    .catch((error) => console.error(error));
});
