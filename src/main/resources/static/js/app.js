$(document).ready(function() {
	changePageAndSize();
});

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