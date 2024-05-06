package com.example.calculator;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.calculator.databinding.FragmentCalculatorBinding;

public class CalculatorFragment extends Fragment {
    private FragmentCalculatorBinding binding;
    private CalculatorViewModel viewModel;

    public CalculatorFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentCalculatorBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = new ViewModelProvider(requireActivity()).get(CalculatorViewModel.class);

        viewModel.getInput().observe(requireActivity(), input -> {
            binding.outlinedTextView.setText(input);
        });

        binding.zeroBtn.setOnClickListener(v -> viewModel.setInput("0"));
        binding.oneBtn.setOnClickListener(v -> viewModel.setInput("1"));
        binding.twoBtn.setOnClickListener(v -> viewModel.setInput("2"));
        binding.threeBtn.setOnClickListener(v -> viewModel.setInput("3"));
        binding.fourBtn.setOnClickListener(v -> viewModel.setInput("4"));
        binding.fiveBtn.setOnClickListener(v -> viewModel.setInput("5"));
        binding.sixBtn.setOnClickListener(v -> viewModel.setInput("6"));
        binding.sevenBtn.setOnClickListener(v -> viewModel.setInput("7"));
        binding.eightBtn.setOnClickListener(v -> viewModel.setInput("8"));
        binding.nineBtn.setOnClickListener(v -> viewModel.setInput("9"));

        binding.plusBtn.setOnClickListener(v -> viewModel.setInput("+"));
        binding.minusBtn.setOnClickListener(v -> viewModel.setInput("-"));
        binding.multiplicationBtn.setOnClickListener(v -> viewModel.setInput("*"));
        binding.divisionBtn.setOnClickListener(v -> viewModel.setInput("/"));

        binding.pointBtn.setOnClickListener(v -> viewModel.setInput("."));
        binding.clearBtn.setOnClickListener(v -> viewModel.setInput("C"));
        binding.backspaceBtn.setOnClickListener(v -> viewModel.setInput("B"));

        binding.equalBtn.setOnClickListener(v -> viewModel.setInput("="));

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}