function generateCalendar(date) {
    const month = date.getMonth();
    const year = date.getFullYear();
    const firstDay = new Date(year, month, 1).getDay();
    const daysInMonth = new Date(year, month + 1, 0).getDate();

    document.getElementById("monthYear").textContent =
        `${date.toLocaleString('default', { month: 'long' })} ${year}`;

    const calendarBody = document.getElementById("calendarBody");
    calendarBody.innerHTML = "";

    const today = new Date();
    const todayDate = today.getDate();
    const todayMonth = today.getMonth();
    const todayYear = today.getFullYear();

    let dateCounter = 1;

    for (let i = 0; i < 6; i++) {
        const row = document.createElement("tr");

        for (let j = 0; j < 7; j++) {
            const cell = document.createElement("td");

            if (i === 0 && j < firstDay) {
                cell.textContent = "";
            } else if (dateCounter <= daysInMonth) {
                const currentDate = new Date(year, month, dateCounter);
                const formattedDate = currentDate.toLocaleDateString('en-GB', {
                    day: '2-digit',
                    month: '2-digit',
                    year: 'numeric'
                });

                const eventsOnDay = Array.isArray(events)
                    ? events.filter(e => e.eventDate === formattedDate)
                    : [];

                const cellContent = document.createElement("div");
                const dayLabel = document.createElement("strong");
                dayLabel.textContent = dateCounter;
                cellContent.appendChild(dayLabel);

                eventsOnDay.forEach(event => {
                    const eventDiv = document.createElement("div");
                    eventDiv.style.fontSize = "0.7rem";
                    eventDiv.style.marginTop = "4px";
                    eventDiv.innerHTML = `
                        <div><strong>${event.eventName}</strong></div>
                        <div>${event.eventTime} @ ${event.eventLocation}</div>
                    `;
                    cell.classList.add("bg-warning");
                    cellContent.appendChild(eventDiv);
                });

                cell.appendChild(cellContent);

                if (dateCounter === todayDate && month === todayMonth && year === todayYear) {
                    cell.classList.add("bg-info", "text-white", "fw-bold");
                }

                dateCounter++;
            } else {
                cell.textContent = "";
            }

            row.appendChild(cell);
        }

        calendarBody.appendChild(row);
    }
}
