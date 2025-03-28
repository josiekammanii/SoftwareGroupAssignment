// calendar.js
document.addEventListener('DOMContentLoaded', function () {
    const calendarBody = document.getElementById('calendarBody');
    const monthYear = document.getElementById('monthYear');
    const prevMonth = document.getElementById('prevMonth');
    const nextMonth = document.getElementById('nextMonth');

    const today = new Date();
    let currentMonth = today.getMonth();
    let currentYear = today.getFullYear();

    const months = [
        'January', 'February', 'March', 'April', 'May', 'June',
        'July', 'August', 'September', 'October', 'November', 'December'
    ];

    function generateCalendar(month, year) {
        calendarBody.innerHTML = ''; // Clear previous content
        monthYear.textContent = `${months[month]} ${year}`;

        const firstDay = new Date(year, month, 1).getDay(); // Day of week for 1st
        const daysInMonth = new Date(year, month + 1, 0).getDate(); // Total days
        const prevMonthDays = new Date(year, month, 0).getDate(); // Days in prev month

        let date = 1;
        let prevDate = prevMonthDays - firstDay + 1;

        for (let i = 0; i < 6; i++) { // 6 weeks max
            const row = document.createElement('tr');

            for (let j = 0; j < 7; j++) {
                const cell = document.createElement('td');

                if (i === 0 && j < firstDay) {
                    // Previous month's days
                    cell.textContent = prevDate++;
                    cell.classList.add('prev-next-month');
                } else if (date > daysInMonth) {
                    // Next month's days
                    cell.textContent = date - daysInMonth;
                    cell.classList.add('prev-next-month');
                    date++;
                } else {
                    // Current month's days
                    cell.textContent = date;
                    if (date === today.getDate() && month === today.getMonth() && year === today.getFullYear()) {
                        cell.classList.add('current-day');
                    }
                    date++;
                }
                row.appendChild(cell);
            }
            calendarBody.appendChild(row);
            if (date > daysInMonth && i >= 4) break; // Stop if month is done
        }
    }

    // Initial calendar render
    generateCalendar(currentMonth, currentYear);

    // Navigation
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
    });
});