// Obtener referencias a los elementos
const searchText = document.querySelector('#searchText');
const searchSelect = document.querySelector('#searchSelect');

// Agregar controladores de eventos
searchText.addEventListener('input', function() {
  // Si el campo de texto tiene algún valor, deshabilitar el menú desplegable
  if (this.value) {
    searchSelect.disabled = true;
  } else {
    searchSelect.disabled = false;
  }
});

searchSelect.addEventListener('change', function() {
  // Si se seleccionó alguna opción en el menú desplegable, deshabilitar el campo de texto
  if (this.value) {
    searchText.disabled = true;
  } else {
    searchText.disabled = false;
  }
});
// RESET De FORMULARIO
