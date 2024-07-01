var url = "http://localhost:8080/api/v1/prestamo/";

/*document.getElementById("fecha_prestamo").addEventListener("keypress",soloLetras);
document.getElementById("autor_libro").addEventListener("keypress",soloLetras);
document.getElementById("genero_libro").addEventListener("keypress",soloLetras);

function soloLetras(event){
  console.log("Llave presionada: "+event.key);
  console.log("Código tecla: "+event.keyCode);
  
  const letrasPermitidas=[
    //letras en minúsculas
    "a","b","c","d","e","f","g","h","i","j","k","l","m","n","p","q","r","s","t","u","v","x","y","w","o","z","ñ","Ñ",
    //LETRAS EN MAYÚSCULAS
    "A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z", " ",
    //letras con tildes, mayusculas y minusculas
    "á",  "é",  "í",  "ó",  "ú",  "Á",  "É",  "Í",  "Ó",  "Ú"

  ];
  const numeroPermitidos=[
    '1', '2', '3','4','5','6','7','8','9','0'
  ];
  const caracteresPermitidos=[
    '@','_','-','.'
  ];


  if (!(letrasPermitidas.includes(event.key))){
    event.preventDefault();
    return;
  }

}*/
function listarPrestamo() {
  var capturarFiltro = document.getElementById("inputSearch").value;
  var urlLocal = url;
  if (capturarFiltro != "") {
    urlLocal += "busquedafiltro/" + capturarFiltro;
  }

  $.ajax({
    url: urlLocal,
    type: "GET",
    success: function(result) {
      console.log(result);

      var cuerpoTabla = document.getElementById("cuerpoTabla");
      cuerpoTabla.innerHTML = "";

      for (var i = 0; i < result.length; i++) {
        var trResgistro = document.createElement("tr");

        var celdaId = document.createElement("td");
        let celdaIdUsuario = document.createElement("td");
        let celdaIdLibro = document.createElement("td");
        let celdaFechaPrestamo = document.createElement("td");
        let celdaFechaDevolucion = document.createElement("td");
        let celdaEstado = document.createElement("td");

        let celdaOpcionEditar = document.createElement("td");
        let botonEditarPrestamo = document.createElement("button");
        botonEditarPrestamo.value = result[i]["id_prestamo"];
        botonEditarPrestamo.innerHTML = "Editar";
        botonEditarPrestamo.onclick = function(e) {
          $('#exampleModal').modal('show');
          consultarLibroID(this.value);
        }
        botonEditarPrestamo.className = "btn btn-warning editar-prestamo";
        celdaOpcionEditar.appendChild(botonEditarPrestamo);

        let celdaOpcionEliminar = document.createElement("td");
        let botonEliminarPrestamo = document.createElement("button");
        botonEliminarPrestamo.value = result[i]["id_prestamo"];
        botonEliminarPrestamo.innerHTML = "Eliminar";
        botonEliminarPrestamo.onclick = function(e) {
          // Aquí deberías escribir la lógica para eliminar el libro con el id correspondiente
          // Puedes usar una función separada para realizar la eliminación
          eliminarPrestamo(this.value);
        }
        botonEliminarPrestamo.className = "btn btn-danger eliminar-prestamo";
        celdaOpcionEliminar.appendChild(botonEliminarPrestamo);

        celdaId.innerText = result[i]["id_prestamo"];
        celdaIdUsuario.innerText = result[i]["usuario"]["nombre_usuario"];
        celdaIdLibro.innerText = result[i]["libro"]["titulo_libro"];
        celdaFechaPrestamo.innerText = result[i]["fecha_prestamo"];
        celdaFechaDevolucion.innerText = result[i]["fecha_devolucion"];
        celdaEstado.innerText = result[i]["estado_prestamo"];

        trResgistro.appendChild(celdaId);
        trResgistro.appendChild(celdaIdUsuario);
        trResgistro.appendChild(celdaIdLibro);
        trResgistro.appendChild(celdaFechaPrestamo);
        trResgistro.appendChild(celdaFechaDevolucion);
        trResgistro.appendChild(celdaEstado);
        trResgistro.appendChild(celdaOpcionEditar);
        trResgistro.appendChild(celdaOpcionEliminar);

        cuerpoTabla.appendChild(trResgistro);
      }
    },
    error: function(error) {
      alert("Error en la petición " + error);
    }
  });
}


function eliminarPrestamo(idPrestamo) {
  // Confirmar con el usuario antes de eliminar
  Swal.fire({
    title: "¿Estás seguro?",
    text: "¿Deseas eliminar este prestamo?",
    icon: "warning",
    showCancelButton: true,
    confirmButtonColor: "#3085d6",
    cancelButtonColor: "#d33",
    confirmButtonText: "Sí, eliminar",
    cancelButtonText: "Cancelar"
  }).then((result) => {
    if (result.isConfirmed) {
      // Realizar la petición AJAX para eliminar el prestamo
      $.ajax({
        url: url + idPrestamo,
        type: "DELETE",
        success: function(response) {
          // Mostrar mensaje de confirmación
          Swal.fire({
            title: "¡Eliminado!",
            text: "El préstamo ha sido eliminado correctamente.",
            icon: "success"
          });
          // Volver a cargar la lista de préstamos después de eliminar
          listarPrestamo();
        },
        error: function(error) {
          // Mostrar mensaje de error si la petición falla
          Swal.fire("Error", "Error al eliminar el préstamo. " + error.responseText, "error");
        }
      });
    }
  });
}




//
function consultarPrestamoID(id){
  //alert(id);
  $.ajax({
      url:url+id,
      type:"GET",
      success: function(result){
          document.getElementById("id_prestamo").value=result["id_prestamo"];
          document.getElementById("id_usuario").value=result["usuario"]["id_usuario"];
          document.getElementById("id_libro").value=result["libro"]["id_libro"];
          document.getElementById("fecha_prestamo").value=result["fecha_prestamo"];
          document.getElementById("fecha_devolucion").value=result["fecha_devolucion"];
          document.getElementById("estado_prestamo").value=result["estado_prestamo"];
      }
  });
}
//2.Crear petición que actualice la información del préstamo

function actualizarPrestamo() { 
  var id_prestamo=document.getElementById("id_ingreso").value
  let formData={ 
    "usuario": document.getElementById("id_usuario").value,
    "libro": document.getElementById("id_libro").value,
    "fecha_prestamo": document.getElementById("fecha_prestamo").value,
    "fecha_devolucion": document.getElementById("fecha_devolucion").value,
    "estado": document.getElementById("estado").value

};

if (validarCampos()) {
  $.ajax({
      url:url+id_prestamo,
      type: "PUT",
      data: formData,
      success: function(result) {
          // Manejar la respuesta exitosa según necesites
          Swal.fire({
              title: "¡Excelente!",
              text: "Se guardó correctamente",
              icon: "success"
            });
          // Puedes hacer algo adicional como recargar la lista de médicos
          listarIngreso();
      },
      error: function(error) {
          // Manejar el error de la petición
          Swal.fire({
              title: "¡Error!",
              text: "No se guardó",
              icon: "error"
            });
      }
  });
  } else {
  Swal.fire({
      title: "¡Error!",
      text: "Llene todos los campos correctamente",
      icon: "error"
    });
  }
}

function limpiarPrestamo() {
  document.getElementById("id_usuario").className="form-control";
  document.getElementById("id_libro").className="form-control";
  document.getElementById("fecha_prestamo").className="form-control";
  document.getElementById("fecha_devolucion").className="form-control";
  document.getElementById("estado_prestamo").className="form-control";


  document.getElementById("id_usuario").value = "";
  document.getElementById("id_libro").value = "";
  document.getElementById("fecha_prestamo").value = "";
  document.getElementById("fecha_devolucion").value = "";
  document.getElementById("estado_prestamo").value = "";
  document.getElementById("numero_ejemplares_ocupados").value = "";
}
