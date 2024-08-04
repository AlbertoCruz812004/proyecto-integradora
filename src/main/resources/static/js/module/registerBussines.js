const registerBussines = async (request) => {
    const response = await fetch("http://localhost:8080/auth/sign-up", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(request),
    });

    const data = await response.json();

    return data;
};