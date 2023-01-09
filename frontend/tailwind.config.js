/** @type {import('tailwindcss').Config} */
module.exports = {
  content: [
    "./index.html",
    "./src/**/*.{vue,js,ts,jsx,tsx}", 
  ],
  theme: {
    extend: {
      backgroundImage: {
        'banner': "url('../img/banner.jpg')",
      }, 
      colors: {
        'dark-brown': '#261515',
        'clear-brown': '#3A1900',
        'd-color':'#897564'

      },
      
    },
  },
  plugins: [],
}
