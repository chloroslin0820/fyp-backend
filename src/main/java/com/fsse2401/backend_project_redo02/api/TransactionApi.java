package com.fsse2401.backend_project_redo02.api;

import com.fsse2401.backend_project_redo02.data.transaction.domainObject.StatusTransactionResData;
import com.fsse2401.backend_project_redo02.data.transaction.dto.StatusTransactionResDto;
import com.fsse2401.backend_project_redo02.data.transaction.dto.TransactionResDto;
import com.fsse2401.backend_project_redo02.data.user.domainObject.FirebaseUserData;
import com.fsse2401.backend_project_redo02.service.TransactionService;
import com.fsse2401.backend_project_redo02.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transaction")
public class TransactionApi {
    private TransactionService transactionService;
    @Autowired
    public TransactionApi(TransactionService transactionService){
        this.transactionService = transactionService;
    }

    @PostMapping("/prepare")
    public TransactionResDto prepareTransaction(JwtAuthenticationToken token){
        return new TransactionResDto(
                transactionService.prepareTransaction(
                        JwtUtil.getFirebaseUserData(token)
                )
        );
    }

    @GetMapping("/{tid}")
    public TransactionResDto getTransaction(JwtAuthenticationToken jwtToken, @PathVariable Integer tid){
        return new TransactionResDto(transactionService.getTransaction(new FirebaseUserData(jwtToken), tid));
    }

    @PatchMapping("/{tid}/pay")
    public StatusTransactionResDto payTransaction(JwtAuthenticationToken jwtToken, @PathVariable Integer tid){
        return new StatusTransactionResDto(new StatusTransactionResData(transactionService.payTransaction(new FirebaseUserData(jwtToken), tid)));
    }

    @PatchMapping("/{tid}/finish")
    public TransactionResDto finishTransaction(JwtAuthenticationToken jwtToken, @PathVariable Integer tid){
        return new TransactionResDto(transactionService.finishTransaction(new FirebaseUserData(jwtToken), tid));
    }

}
