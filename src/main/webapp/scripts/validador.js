/**
 * Validação de formulário
 * @author Maurício Lopes
 */

 function validar(){
	 let nome = frmJogo.nome.value
	 let plataforma = frmJogo.plataforma.value
	 let desenvolvedor = frmJogo.desenvolvedor.value
	 if (nome === "") {
		 alert('Preencha o campo Nome')
		 frmJogo.nome.focus()
		 return false
	 }else if (plataforma === ""){
		 alert('Preencha o campo Plataforma')
		 frmJogo.plataforma.focus()
		 return false		 
	 }else if (desenvolvedor === ""){
		 alert('Preencha o campo Desenvolvedor')
		 frmJogo.desenvolvedor.focus()
		 return false
	 }else{
		 document.forms["frmJogo"].submit()
	 }
	 
 }