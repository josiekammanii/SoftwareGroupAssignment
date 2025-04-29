function generateCalendar(month, year) {
    const firstDay = new Date(year, month, 1).getDay();
    const daysInMonth = new Date(year, month + 1, 0).getDate();
    let date = 1;

    calendarBody.innerHTML = "";
    monthYear.textContent = `${new Date(year, month).toLocaleString("default", {month: "long"})} ${year}`;

    for (let i = 0; i < 6; i++) {
        const row = document.createElement("tr");

        for (let j = 0; j < 7; j++) {
            const cell = document.createElement("td");

            if (i === 0 && j < firstDay) {
                cell.textContent = "";
            } else if (date > daysInMonth) {
                cell.textContent = "";
            } else {
                const displayDate = `${String(date).padStart(2, '0')}-${String(month + 1).padStart(2, '0')}-${year}`;
                const eventsForDay = events.filter(e => e.eventDate === displayDate);

                cell.innerHTML = `<strong>${date}</strong>`;
                if (eventsForDay.length > 0) {
                    cell.classList.add("bg-warning");

                    eventsForDay.forEach(event => {
                        const eventBtn = document.createElement("button");
                        eventBtn.textContent = event.eventName;
                        eventBtn.className = "btn btn-sm btn-link d-block text-start p-0";
                        eventBtn.onclick = () => {
                            alert(`Event: ${event.eventName}\nDate: ${event.eventDate}\nPlace: ${event.place}`);
                        };
                        cell.appendChild(document.createElement("br"));
                        cell.appendChild(eventBtn);
                    });
                }

                if (
                    date === today.getDate() &&
                    month === today.getMonth() &&
                    year === today.getFullYear()
                ) {
                    cell.classList.add("bg-info", "text-white", "fw-bold");
                }

                date++;
            }

            row.appendChild(cell);
        }

        calendarBody.appendChild(row);
    }
}
