document.getElementById("login-form").addEventListener("submit", async function (e) {
    e.preventDefault(); // Stop form from refreshing page

    const name = document.getElementById("name").value;
    const dob = document.getElementById("dob").value;

    try {
        const response = await fetch("http://localhost:8080/api/login", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify({ name: name, dob: dob })
        });

        const messageElement = document.getElementById("message");

        if (response.ok) {
            const data = await response.json();
            messageElement.textContent = "Login successful! Welcome " + data.name;
            console.log("Pupil:", data);
        } else {
            const errorText = await response.text();
            messageElement.textContent = "Login failed: " + errorText;
        }
    } catch (err) {
        console.error("Error:", err);
        document.getElementById("message").textContent = "Could not connect to server.";
    }
});
