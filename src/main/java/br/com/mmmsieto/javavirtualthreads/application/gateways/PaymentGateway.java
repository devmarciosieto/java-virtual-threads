package br.com.mmmsieto.javavirtualthreads.application.gateways;

import br.com.mmmsieto.javavirtualthreads.domain.PaymentMethod;

public interface PaymentGateway {

    Output execute(Input input);

    record Input(PaymentMethod paymentMethod) {}

    record Output(String transactionId) {}

}
