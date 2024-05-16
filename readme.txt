Important Note:- GitHub didn't allow me to push the files because one of them was large in size so the larger file is uploaded in my drive and the requested you to please download it and paste it in the path:src/main/resources.(To covert the text to vector data it is mainly required) 
drive link is "https://drive.google.com/file/d/12Hzlqb7jUjHr9u_7NNozvNn4b3Wyrcio/view?usp=sharing"

# Text To Vector Conversion

This project is a simple web application that allows users to convert text input into vectors and store them in a Supabase database.

## Features

- Users can enter text in the input field.
- Upon submitting the text, it will be processed and converted into vectors.
- The vectors are stored in a Supabase database for future reference.
- The application also includes a chatbot feature powered by OpenAI's ChatGPT.

## Technologies Used

- HTML/CSS/JavaScript
- Java (for backend processing)
- OpenAI API for chatbot feature
  ("Because it is not a open source it is refusing to connect")
- Supabase for vector database storage

## Setup

To run this project locally, follow these steps:

1. Clone this repository to your local machine.
2. Make sure you have Java installed on your machine.
3. Set up a Supabase account and create a new project with a vector database.
4. Set up environment variables for your Supabase project.
5. Run the Java server.
6. Open the src/main/webapp/ `index.jsp` file in your web browser.

## Usage

- Enter the text you want to convert into the input field.
- Click the "Submit" button to process the text and convert it into vectors.
- The vectors are automatically stored in the Supabase database.
- You can also start a conversation with the chatbot by clicking the chat icon.

## Contributing

Contributions are welcome! If you have any ideas for improvements or new features, feel free to submit a pull request.

Note:- Sorry to say this but as per the requirement of the project I am not hitting the API to convert the text into vector because the API's which was searched by me to embed the text all of them where paid version and I was not abled to afford it so I tried it and got a 
error message 
"Response code: 429
        "message": "You exceeded your current quota, please check your plan and billing details. For more information on this error, read the                                  				docs:https://platform.openai.com/docs/guides/error-codes/api-errors. 
So, I downloaded the pre-trained API model from the internet and using it to convert the text to vector data and I am storing it to a cloud
called as supabase vector database.

As per the given time without knowing anything about the requirements I researched about it and done my best in the given time if given an opportunity to move ahead in the process, I will be more than willing to explore more and do give my best to achieve the companies expectation.