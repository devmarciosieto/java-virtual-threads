package br.com.mmmsieto.javavirtualthreads.infrastructure.gateways;

import br.com.mmmsieto.javavirtualthreads.application.gateways.PaymentGateway;
import br.com.mmmsieto.javavirtualthreads.domain.PaymentMethod;

public class SmartPaymentGateway implements PaymentGateway {


    @Override
    public Output execute(final Input input) {

        var gateway = switch (input.paymentMethod()) {
            case PaymentMethod.CreditCard cc when "visa".equalsIgnoreCase(cc.brand()) -> new VisaGateway();
            case PaymentMethod.CreditCard cc when "mastercard".equalsIgnoreCase(cc.brand()) -> new MastercardGateway();
            case PaymentMethod.CreditCard cc -> new EloGateway();
            case PaymentMethod.PIX p -> new PIXGateway();
        };

        return gateway.execute(input);
    }

}
