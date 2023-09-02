// Select the 'generateSummary' and 'generateImpPoints' buttons, and the element to display generated data
const btn1 = document.querySelector('.generateSummary');
const btn2 = document.querySelector('.generateImpPoints');
const disp = document.getElementById('generatedData');
const copyToClipboardButton = document.getElementById('copyToClipboard');

// Define the API URL
const apiUrl = "http://localhost:8080/summary/";

// Function to retrieve the current tab's URL using a Promise
function getTabUrl() {
    return new Promise((resolve, reject) => {
        // Query the Chrome tabs API to get the active tab's URL in the current window
        chrome.tabs.query({ active: true, currentWindow: true }, function (tabs) {
            if (tabs[0] && tabs[0].url) {
                // Resolve the Promise with the URL if available
                resolve(tabs[0].url);
            } else {
                // Reject the Promise with an error if the URL is not available
                reject(new Error("Unable to retrieve tab URL"));
            }
        });
    });
}


// Event listener for the 'generateSummary' button click
btn1.addEventListener('click',() =>{
    disp.innerText="Loading...";
    getTabUrl()
    // Fetch data from the API endpoint for generating summaries
    .then(async (url)=>{
        try{
            await fetch(apiUrl + "getSummary" ,{
                method: 'POST',
                headers: {
                  'Content-Type': 'text/plain',
                },
                body: url
            })
            .then((response)=>{
                return response.text();
            })
            .then(data=>{
                disp.innerText = data;
                copyToClipboardButton.style.display = 'block';
            })
        }catch(error){
            console.error(`fetch error : ${error.message}`);
        }
    })
});




//Event listener for the 'generateImpPoints' button click
btn2.addEventListener('click',() =>{
    disp.innerText="Loading...";
    getTabUrl()
    // Fetch data from the API endpoint for generating important points
    .then(async (url)=>{
        try{
            fetch(apiUrl+"getImptPoints",{
                method: 'POST',
                headers: {
                'Content-Type': 'text/plain',
                },
                body: url
            })
            .then((response)=>{
                return response.text();
            })
            .then(data=>{
                disp.innerText = data;
                copyToClipboardButton.style.display = 'block';
            })
        }catch(error){
            console.error(`fetch error : ${error.message}`);
        }
    })
});


// Function to copy the generated text to the clipboard
// Add a click event listener to the "Copy to Clipboard" button
copyToClipboardButton.addEventListener('click', () => {
    const textToCopy = generatedData.textContent;

    // Check if the Clipboard API is available
    if (navigator.clipboard) {
        navigator.clipboard.writeText(textToCopy)
            .then(() => {
                // Successfully copied to clipboard
                alert('Copied to clipboard!');
            })
            .catch((error) => {
                console.error('Unable to copy to clipboard:', error);
            });
    } else {
        // Clipboard API not available, provide fallback behavior here
        alert('Clipboard API not supported by your browser.');
    }
});