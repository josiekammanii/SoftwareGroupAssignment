// calendar.js

document.addEventListener('DOMContentLoaded', async function () {
    const calendarBody = document.getElementById('calendarBody');
    const monthYear = document.getElementById('monthYear');
    const prevMonth = document.getElementById('prevMonth');
    const nextMonth = document.getElementById('nextMonth');

    const today = new Date();
    let currentMonth = today.getMonth();
    let currentYear = today.getFullYear();
    let events = [];

    const months = [
        'January', 'February', 'March', 'April', 'May', 'June',
        'July', 'August', 'September', 'October', 'November', 'December'
    ];

    const pupil = JSON.parse(localStorage.getItem("pupil"));
    if (pupil) {
        try {
            const response = await fetch(`http://localhost:8080/api/events/${pupil.cohortId}`);
            events = await response.json();
        } catch (error) {
            console.error("Could not load events", error);
        }
    }

    function generateCalendar(month, year) {
        const firstDay = new Date(year, month, 1).getDay(); // 0 = Sun, 1 = Mon, ...
        const daysInMonth = new Date(year, month + 1, 0).getDate(); // How many days in month
        let date = 1;

        calendarBody.innerHTML = "";
        monthYear.textContent = `${new Date(year, month).toLocaleString("default", {month: "long"})} ${year}`;

        // 6 weeks to cover all cases
        for (let i = 0; i < 6; i++) {
            const row = document.createElement("tr");

            for (let j = 0; j < 7; j++) {
                const cell = document.createElement("td");

                // Skip cells before first day of the month
                if (i === 0 && j < firstDay) {
                    cell.textContent = "";
                } else if (date > daysInMonth) {
                    // Don't add more dates once we pass the max
                    cell.textContent = "";
                } else {
                    cell.textContent = date;

                    // OPTIONAL: Highlight today
                    if (
                        date === today.getDate() &&
                        month === today.getMonth() &&
                        year === today.getFullYear()
                    ) {
                        cell.classList.add("current-day");
                    }

                    date++; // move to next date
                }

                row.appendChild(cell);
            }

            calendarBody.appendChild(row);
        }
        events.forEach(event => {
            const eventDate = new Date(event.eventDate);
            if (eventDate.getMonth() === month && eventDate.getFullYear() === year) {
                const dayCells = calendarbody.getElementsByTagName('td');
                Array.from(dayCells).forEach(cell => {
                    if (parseInt(cell.textContent) === eventDate.getDate()) {
                        cell.classList.add('event-day');
                        cell.setAttribute("title, event.eventName");
                    }
                });
            }
        });
    }

    generateCalendar(currentMonth, currentYear);

    prevMonth.addEventListener('click', function () {
        currentMonth--;
        if (currentMonth < 0) {
            currentMonth = 11;
            currentYear--;
        }
        generateCalendar(currentMonth, currentYear);
    });

    nextMonth.addEventListener('click', function () {
        currentMonth++;
        if (currentMonth > 11) {
            currentMonth = 0;
            currentYear++;
        }
        generateCalendar(currentMonth, currentYear);

        console.log(pupil.cohortId);
    });
});
