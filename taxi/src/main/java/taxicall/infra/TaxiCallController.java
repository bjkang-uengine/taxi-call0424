package taxicall.infra;

import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import taxicall.domain.*;

@RestController
// @RequestMapping(value="/taxiCalls")
@Transactional
public class TaxiCallController {

    @Autowired
    TaxiCallRepository taxiCallRepository;

    @RequestMapping(
        value = "taxiCalls/{id}/택시탑승",
        method = RequestMethod.PUT,
        produces = "application/json;charset=UTF-8"
    )
    public TaxiCall boarding(
        @PathVariable(value = "id") Long id,
        @RequestBody BoardingCommand boardingCommand,
        HttpServletRequest request,
        HttpServletResponse response
    ) throws Exception {
        System.out.println("##### /taxiCall/boarding  called #####");
        Optional<TaxiCall> optionalTaxiCall = taxiCallRepository.findById(id);

        optionalTaxiCall.orElseThrow(() -> new Exception("No Entity Found"));
        TaxiCall taxiCall = optionalTaxiCall.get();
        taxiCall.boarding(boardingCommand);

        taxiCallRepository.save(taxiCall);
        return taxiCall;
    }

    @RequestMapping(
        value = "taxiCalls/{id}/목적지도착",
        method = RequestMethod.PUT,
        produces = "application/json;charset=UTF-8"
    )
    public TaxiCall arrival(
        @PathVariable(value = "id") Long id,
        @RequestBody ArrivalCommand arrivalCommand,
        HttpServletRequest request,
        HttpServletResponse response
    ) throws Exception {
        System.out.println("##### /taxiCall/arrival  called #####");
        Optional<TaxiCall> optionalTaxiCall = taxiCallRepository.findById(id);

        optionalTaxiCall.orElseThrow(() -> new Exception("No Entity Found"));
        TaxiCall taxiCall = optionalTaxiCall.get();
        taxiCall.arrival(arrivalCommand);

        taxiCallRepository.save(taxiCall);
        return taxiCall;
    }

    @RequestMapping(
        value = "taxiCalls/",
        method = RequestMethod.POST,
        produces = "application/json;charset=UTF-8"
    )
    public TaxiCall paymentRequest(
        HttpServletRequest request,
        HttpServletResponse response,
        @RequestBody TaxiCall taxiCall
    ) throws Exception {
        System.out.println("##### /taxiCall/paymentRequest  called #####");
        taxiCall.paymentRequest(paymentRequestcommand);
        taxiCallRepository.save(taxiCall);
        return taxiCall;
    }

    @RequestMapping(
        value = "taxiCalls/{id}/accept",
        method = RequestMethod.PUT,
        produces = "application/json;charset=UTF-8"
    )
    public TaxiCall accept(
        @PathVariable(value = "id") Long id,
        @RequestBody AcceptCommand acceptCommand,
        HttpServletRequest request,
        HttpServletResponse response
    ) throws Exception {
        System.out.println("##### /taxiCall/accept  called #####");
        Optional<TaxiCall> optionalTaxiCall = taxiCallRepository.findById(id);

        optionalTaxiCall.orElseThrow(() -> new Exception("No Entity Found"));
        TaxiCall taxiCall = optionalTaxiCall.get();
        taxiCall.accept(acceptCommand);

        taxiCallRepository.save(taxiCall);
        return taxiCall;
    }

    @RequestMapping(
        value = "taxiCalls/{id}/출발",
        method = RequestMethod.PUT,
        produces = "application/json;charset=UTF-8"
    )
    public TaxiCall departure(
        @PathVariable(value = "id") Long id,
        @RequestBody DepartureCommand departureCommand,
        HttpServletRequest request,
        HttpServletResponse response
    ) throws Exception {
        System.out.println("##### /taxiCall/departure  called #####");
        Optional<TaxiCall> optionalTaxiCall = taxiCallRepository.findById(id);

        optionalTaxiCall.orElseThrow(() -> new Exception("No Entity Found"));
        TaxiCall taxiCall = optionalTaxiCall.get();
        taxiCall.departure(departureCommand);

        taxiCallRepository.save(taxiCall);
        return taxiCall;
    }
}
