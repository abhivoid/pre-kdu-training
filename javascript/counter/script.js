let counter = 0;

        document.addEventListener('DOMContentLoaded', () => {
            document.getElementById('increment').addEventListener('click', () => {
                counter++;
                document.getElementById('counter-value').innerText = counter;
            });

            document.getElementById('decrement').addEventListener('click', () => {
                counter--;
                document.getElementById('counter-value').innerText = counter;
            });
        });