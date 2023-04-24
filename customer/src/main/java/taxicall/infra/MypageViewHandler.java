package taxicall.infra;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;
import taxicall.config.kafka.KafkaProcessor;
import taxicall.domain.*;

@Service
public class MypageViewHandler {

    @Autowired
    private MypageRepository mypageRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void whenCalled_then_CREATE_1(@Payload Called called) {
        try {
            if (!called.validate()) return;

            // view 객체 생성
            Mypage mypage = new Mypage();
            // view 객체에 이벤트의 Value 를 set 함
            mypage.setCallid(String.valueOf(called.getId()));
            mypage.setUserLocation(called.getUserLocation());
            mypage.setDestination(called.getDestination());
            mypage.setStatus(called.getStatus());
            // view 레파지 토리에 save
            mypageRepository.save(mypage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @StreamListener(KafkaProcessor.INPUT)
    public void whenDepartured_then_UPDATE_1(@Payload Departured departured) {
        try {
            if (!departured.validate()) return;
            // view 객체 조회

            List<Mypage> mypageList = mypageRepository.findByCallid(
                String.valueOf(departured.getCallid())
            );
            for (Mypage mypage : mypageList) {
                // view 객체에 이벤트의 eventDirectValue 를 set 함
                mypage.setStatus(departured.getStatus());
                mypage.setTaxiNumber(departured.getNumber());
                mypage.setDriverName(departured.getDriverName());
                mypage.setDriverMobileNumber(
                    departured.getDriverMobileNumber()
                );
                // view 레파지 토리에 save
                mypageRepository.save(mypage);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @StreamListener(KafkaProcessor.INPUT)
    public void whenDepartured_then_UPDATE_2(@Payload Departured departured) {
        try {
            if (!departured.validate()) return;
            // view 객체 조회

            List<Mypage> mypageList = mypageRepository.findByCallid(
                String.valueOf(departured.getCallid())
            );
            for (Mypage mypage : mypageList) {
                // view 객체에 이벤트의 eventDirectValue 를 set 함
                mypage.setStatus(departured.getStatus());
                // view 레파지 토리에 save
                mypageRepository.save(mypage);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @StreamListener(KafkaProcessor.INPUT)
    public void whenBoardinged_then_UPDATE_3(@Payload Boardinged boardinged) {
        try {
            if (!boardinged.validate()) return;
            // view 객체 조회

            List<Mypage> mypageList = mypageRepository.findByCallid(
                String.valueOf(boardinged.getCallid())
            );
            for (Mypage mypage : mypageList) {
                // view 객체에 이벤트의 eventDirectValue 를 set 함
                mypage.setStatus(boardinged.getStatus());
                // view 레파지 토리에 save
                mypageRepository.save(mypage);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @StreamListener(KafkaProcessor.INPUT)
    public void whenArrivaled_then_UPDATE_4(@Payload Arrivaled arrivaled) {
        try {
            if (!arrivaled.validate()) return;
            // view 객체 조회

            List<Mypage> mypageList = mypageRepository.findByCallid(
                arrivaled.getCallid()
            );
            for (Mypage mypage : mypageList) {
                // view 객체에 이벤트의 eventDirectValue 를 set 함
                mypage.setStatus(arrivaled.getStatus());
                // view 레파지 토리에 save
                mypageRepository.save(mypage);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
