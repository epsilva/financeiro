$(document).ready(function() {
	changePageAndSize();
	changeAno();
});

function changeAno(){
	$('#comboAno').change(function() {
		var valor = $("option:selected", this).text();
		var modal = $('#panelMain');
		var form = modal.find('form');
		var action = form.data('url-base');
		if (!action.endsWith('/')) {
			action += '/';
		}
		var ano = "ano/";
		form.attr('action', action + ano + valor).submit();
		console.log(action + valor);
	});
}

function changePageAndSize() {
	$('#pageSizeSelect').change(function(evt) {
		window.location.replace("/financeiro/moedas/?pageSize=" + this.value + "&page=1");
	});
}

$('#confirmacaoExclusaoModal').on(
		'show.bs.modal',
		function(event) {
			var button = $(event.relatedTarget);

			var codigoMoeda = button.data('codigo');
			var dataDeposito = button.data('datadeposito');

			var modal = $(this);
			var form = modal.find('form');
			var action = form.data('url-base');
			if (!action.endsWith('/')) {
				action += '/';
			}

			form.attr('action', action + codigoMoeda);

			modal.find('.modal-body span').html(
					'Tem certeza que deseja excluir o depósito do dia <strong>'
							+ dataDeposito + '</strong>?');
			console.log("Action: " + action + "  " + codigoMoeda);
});

$(function(){
	$('.js-currency').maskMoney({decimal: ',', thousands: '.', allowZero: true});
//	$('.js-editar-calendario').on('click', function(event){
//		event.preventDefault();
//		var botaoReceber = $(event.currentTarget);
//		var urlReceber = botaoReceber.attr('href');
//		
//		var response = $.ajax({
//			url: urlReceber,
//			type: 'PUT'
//		});
//		
//		response.done(function(e){
//			$('#modalCadastro').on('show.bs.modal', function(event) {
//				console.log('teste');
//				var button = $(event.relatedTarget);
//				var modal = $(this);
//				var codigo = button.data('codigo');
//				var form = modal.find('form');
//				modal.show();
//				
//			});
//			
//			
////			var codigoTitulo = botaoReceber.data('codigo');
////			$('[id=' + codigoTitulo + ']').html('<span class="label label-success">'+e+'</span>');
////			botaoReceber.hide();
//		});
//		response.fail(function(e){
//			console.log(e);
//			alert('Erro ao receber cobrança');
//		});
//	});
});

$('#confirmacaoExclusaoModalDesafioSemana').on(
		'show.bs.modal',
		function(event) {
			var button = $(event.relatedTarget);

			var codigoMoeda = button.data('codigo');
			var dataDeposito = button.data('datadeposito');

			var modal = $(this);
			var form = modal.find('form');
			var action = form.data('url-base');
			if (!action.endsWith('/')) {
				action += '/';
			}

			form.attr('action', action + codigoMoeda);

			modal.find('.modal-body span').html(
					'Tem certeza que deseja excluir o depósito do dia <strong>'
							+ dataDeposito + '</strong>?');
			console.log("Action: " + action + "  " + codigoMoeda);
});

$('#confirmacaoExclusaoModalCalendario').on(
		'show.bs.modal',
		function(event) {
			var button = $(event.relatedTarget);

			var codigo = button.data('codigo');
			var descricao = button.data('descricao');

			var modal = $(this);
			var form = modal.find('form');
			var action = form.data('url-base');
			if (!action.endsWith('/')) {
				action += '/';
			}

			form.attr('action', action + codigo);

			modal.find('.modal-body span').html(
					'Tem certeza que deseja excluir a conta <strong>'
							+ descricao + '</strong>?');
			console.log("Action: " + action + "  " + codigo);
});


$('#modalCadastro').on('show.bs.modal', function(event) {
	var button = $(event.relatedTarget);
	var modal = $(this);
	var codigo = button.data('codigo');
	var data = button.data('data');
	var descricao = button.data('descricao');
	var valor = button.data('valor');
	var form = modal.find('form');
	
	modal.find('.panel-body input').val(codigo);
//	modal.find('.data').val(data);
//	modal.find('.descricao').val(descricao);
//	modal.find('.valor').val(valor);
	
	
});

//$('.botao').on('click', function(event) {
//	
//	
//	$.ajax({
//		url: '/calendario/novo/'+codigo,
//        method: 'GET'
//    }).success(function(response) {
//    	modal.find('[name="codigo"]').val(response.codigo).end()
//    	modal.find('[name="descricao"]').val(response.descricao).end()
//    	modal.find('[name="data"]').val(response.data).end()
//    	modal.find('[name="valor"]').val(response.valor).end()
//    	
//    	
//    });
//});


//$(function(){
//	$('[rel="tooltip"]').tooltip();
//	$('.js-currency').maskMoney({decimal: ',', thousands: '.', allowZero: true});
//	$('.js-atualizar-status').on('click', function(event){
//		event.preventDefault();
//		var botaoReceber = $(event.currentTarget);
//		var urlReceber = botaoReceber.attr('href');
//		
//		var response = $.ajax({
//			url: urlReceber,
//			type: 'PUT'
//		});
//		
//		response.done(function(e){
//			var codigoTitulo = botaoReceber.data('codigo');
//			$('[data-role=' + codigoTitulo + ']').html('<span class="label label-success">'+e+'</span>');
//			botaoReceber.hide();
//		});
//		response.fail(function(e){
//			console.log(e);
//			alert('Erro ao receber cobrança');
//		});
//	});
//});






//function Mudarestado(el) {
//    var display = document.getElementById(el).style.display;
//    if(display == "none")
//        document.getElementById(el).style.display = 'block';
//    else
//        document.getElementById(el).style.display = 'none';
//}





//$('#detalhesCalendario').on(
//		'show.bs.modal',
//		function(event) {
//			var button = $(event.relatedTarget);
//
//			var codigoMoeda = button.data('janeiro');
//			var dataDeposito = button.data('janeiro');
//
//			var modal = $(this);
//			var form = modal.find('form');
//			var action = form.data('url-base');
//
//			form.attr('action', action + codigoMoeda);
//
//			modal.find('.modal-body span').html(
//					'Tem certeza que deseja excluir o depósito do dia <strong>'
//							+ dataDeposito + '</strong>?');
//});


