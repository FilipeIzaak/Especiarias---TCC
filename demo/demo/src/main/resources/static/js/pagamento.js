document.addEventListener('DOMContentLoaded', function () {
    const form = document.getElementById('payment-form');
    const paymentMethod = document.getElementById('payment-method');
    const cardDetails = document.getElementById('card-details');
    const pixDetails = document.getElementById('pix-details');
    const boletoDetails = document.getElementById('boleto-details');

    paymentMethod.addEventListener('change', function () {
        const selectedMethod = paymentMethod.value;

        // Ocultar todos os detalhes de pagamento
        cardDetails.classList.add('hidden');
        pixDetails.classList.add('hidden');
        boletoDetails.classList.add('hidden');

        // Mostrar os detalhes do método de pagamento selecionado
        if (selectedMethod === 'credit-card' || selectedMethod === 'debit-card') {
            cardDetails.classList.remove('hidden');
        } else if (selectedMethod === 'pix') {
            pixDetails.classList.remove('hidden');
        } else if (selectedMethod === 'boleto') {
            boletoDetails.classList.remove('hidden');
        }
    });

    form.addEventListener('submit', function (event) {
        event.preventDefault();

        const selectedMethod = paymentMethod.value;

        // Aqui você pode adicionar lógica para processar o pagamento com base no método selecionado
        // Normalmente, isso seria feito em um backend seguro

        // Exemplo de feedback
        let message = 'Pagamento processado com sucesso.';
        if (selectedMethod === 'boleto') {
            message = 'Boleto gerado e enviado por e-mail.';
        }
        alert(message);
    });
});

document.addEventListener('DOMContentLoaded', function () {
    const form = document.getElementById('payment-form');
    const paymentMethod = document.getElementById('payment-method');
    const cardDetails = document.getElementById('card-details');
    const pixDetails = document.getElementById('pix-details');
    const boletoDetails = document.getElementById('boleto-details');
    const sendBoletoEmailButton = document.getElementById('send-boleto-email');
    const emailSentMessage = document.getElementById('email-sent-message');

    paymentMethod.addEventListener('change', function () {
        const selectedMethod = paymentMethod.value;

        // Ocultar todos os detalhes de pagamento
        cardDetails.classList.add('hidden');
        pixDetails.classList.add('hidden');
        boletoDetails.classList.add('hidden');

        // Mostrar os detalhes do método de pagamento selecionado
        if (selectedMethod === 'credit-card' || selectedMethod === 'debit-card') {
            cardDetails.classList.remove('hidden');
        } else if (selectedMethod === 'pix') {
            pixDetails.classList.remove('hidden');
        } else if (selectedMethod === 'boleto') {
            boletoDetails.classList.remove('hidden');
        }
    });

    sendBoletoEmailButton.addEventListener('click', function () {
        const email = document.getElementById('boleto-email').value;

        // Simular o envio do e-mail (você pode adicionar sua lógica de envio aqui)
        emailSentMessage.classList.remove('hidden');

        // Ocultar a mensagem após alguns segundos (por exemplo, 3 segundos)
        setTimeout(function () {
            emailSentMessage.classList.add('hidden');
        }, 3000);
    });

    form.addEventListener('submit', function (event) {
        event.preventDefault();

        const selectedMethod = paymentMethod.value;

        // Aqui você pode adicionar lógica para processar o pagamento com base no método selecionado
        // Normalmente, isso seria feito em um backend seguro

        // Exemplo de feedback
        let message = 'Pagamento processado com sucesso.';
        if (selectedMethod === 'boleto') {
            message = 'Boleto gerado e enviado por e-mail.';
        }
        alert(message);
    });
});

function funcao1()
{
var x;
var r=confirm("Escolha um valor!");
if (r==true)
  {
  x="você pressionou OK!";
  }
else
  {
  x="Você pressionou Cancelar!";
  }
document.getElementById("demo").innerHTML=x;
}
