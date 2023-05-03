/**
 * Confirmação de exclusão de um jogo
 * @author Maurício Lopes
 */

 function confirmar(idjogo){
	 let resposta = confirm("Confirma a exclusão desse jogo?")
	 if (resposta === true) {
		 //alert(idjogo)
		 window.location.href = "delete?idjogo=" + idjogo
	 }
 }