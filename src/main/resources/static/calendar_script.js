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
                const formattedDay = String(dateCounter).padStart(2, "0");
                const formattedMonth = String(month + 1).padStart(2, "0");
                const formattedDate = `${formattedDay}-${formattedMonth}-${year}`;

                // Filter multiple events for the day
                const eventsToday = events.filter(e => e.eventDate === formattedDate);

                if (eventsToday.length > 0) {
                    cell.classList.add("bg-warning");
                    let content = `<strong>${dateCounter}</strong>`;
                    for (const evt of eventsToday) {
                        content += `<div style="font-size: 0.75rem;"><strong>${evt.eventName}</strong><br>${evt.eventTime} @ ${evt.eventLocation}</div>`;
                    }
                    cell.innerHTML = content;
                } else {
                    cell.innerHTML = `<strong>${dateCounter}</strong>`;
                }

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

