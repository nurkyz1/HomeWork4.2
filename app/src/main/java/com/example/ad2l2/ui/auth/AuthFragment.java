package com.example.ad2l2.ui.auth;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.os.CountDownTimer;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.ad2l2.R;
import com.example.ad2l2.databinding.FragmentAuthBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;


public class AuthFragment extends Fragment {
private FragmentAuthBinding binding;
private NavController navController;
private PhoneAuthProvider.OnVerificationStateChangedCallbacks callbacks;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        onBackPressed();
        binding = FragmentAuthBinding.inflate(inflater, container, false);
        navController= NavHostFragment.findNavController(this);
        return binding.getRoot();

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
onBackPressed();
        binding.buttonSign.setOnClickListener(v -> {
            getSmsCode(binding.editNumber.getText().toString());
        });
        setCallbacks();

    }

    private void setCallbacks() {
        callbacks= new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            @Override
            public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
            signIn(phoneAuthCredential);
            }

            @Override
            public void onVerificationFailed(@NonNull FirebaseException e) {

            }

            @Override
            public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                super.onCodeSent(s, forceResendingToken);
                clock();
                onBackPressed();
                binding.codeContainer.setVisibility(View.VISIBLE);
                binding.phoneContainer.setVisibility(View.GONE);
                String code = binding.editCode.getText().toString();

                binding.buttonSign.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String code = binding.editCode.getText().toString();
                        if (code.length()==6&& TextUtils.isDigitsOnly(code)){
                            checkSmsCode(s,code);
                        }

                    }
                });
                binding.buttonCode.setOnClickListener(v -> {
                    navController.navigate(R.id.navigation_home);
                });

            }

            @Override
            public void onCodeAutoRetrievalTimeOut(@NonNull String s) {
                super.onCodeAutoRetrievalTimeOut(s);
                clock();
            }
        };
    }

    private  void checkSmsCode(String verificationId, String code){
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verificationId,code);
        signIn(credential);

    }
    private void signIn(PhoneAuthCredential credential){
        FirebaseAuth.getInstance().signInWithCredential(credential).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    navController.navigate(R.id.navigation_home);
                }else {
                    Toast.makeText(requireContext(), "Error"+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private  void  getSmsCode(String phoneNumber ){
        PhoneAuthOptions options =
                PhoneAuthOptions.newBuilder(FirebaseAuth.getInstance())
                        .setPhoneNumber(phoneNumber)       // Phone number to verify
                        .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
                        .setActivity(requireActivity())                 // Activity (for callback binding)
                        .setCallbacks(callbacks)          // OnVerificationStateChangedCallbacks
                        .build();
        PhoneAuthProvider.verifyPhoneNumber(options);
    }
private void clock(){new CountDownTimer(10000,1000){
    public void onTick(long millisUnitFinished){
        binding.time.setText("seconds remaining: "+millisUnitFinished/1000);
    }
    public  void onFinish(){
        binding.time.setText("ready");
    }
}.start();
}
private  void  onBackPressed(){
    requireActivity().getOnBackPressedDispatcher().
            addCallback(
                    getViewLifecycleOwner(),
                    new OnBackPressedCallback(true) {
                        @Override
                        public void handleOnBackPressed() {
                            alert();
                        }
                    });
}
    public void alert(){
        AlertDialog.Builder adg = new AlertDialog.Builder(binding.getRoot().getContext());
        String positive = "Да";
        String negative = "Нет";
        adg.setMessage("Вы хотите выйти ?");
        adg.setPositiveButton(positive, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                requireActivity().finish();
            }
        });
        adg.setNegativeButton(negative, null);
        adg.show();
    }

}
