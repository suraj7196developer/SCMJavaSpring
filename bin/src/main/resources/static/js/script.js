console.log("Script loaded");

const THEME_LIGHT = "light";
const THEME_DARK = "dark";

// Initialize the theme on page load
document.addEventListener("DOMContentLoaded", () => {
  changeTheme(getTheme());
  const changeThemeButton = document.querySelector("#theme_change_button");
  if (changeThemeButton) {
    changeThemeButton.addEventListener("click", toggleTheme);
  }
});

// Toggle the theme between light and dark
function toggleTheme() {
  let currentTheme = getTheme();
  const newTheme = currentTheme === THEME_DARK ? THEME_LIGHT : THEME_DARK;
  changeTheme(newTheme, currentTheme);
}

// Get the theme from localStorage
function getTheme() {
  return localStorage.getItem("theme") || THEME_LIGHT;
}

// Set the theme to localStorage
function setTheme(theme) {
  localStorage.setItem("theme", theme);
}

// Change the current page theme
function changeTheme(theme, oldTheme = "") {
  setTheme(theme);
  const htmlElement = document.querySelector("html");

  if (oldTheme) {
    htmlElement.classList.remove(oldTheme);
  }
  htmlElement.classList.add(theme);

  const changeThemeButton = document.querySelector("#theme_change_button span");
  if (changeThemeButton) {
    changeThemeButton.textContent = theme === THEME_LIGHT ? "Dark" : "Light";
  }
}
