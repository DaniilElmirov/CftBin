package com.example.cftbin.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.cftbin.R
import com.example.cftbin.databinding.FragmentMainActivityBinding
import com.example.cftbin.domain.entity.CardInfo
import kotlinx.coroutines.launch

class MainActivityFragment : Fragment() {
    private var _binding: FragmentMainActivityBinding? = null
    private val binding: FragmentMainActivityBinding
        get() = _binding ?: throw RuntimeException("Binding == null")

    private val viewModel by lazy {
        ViewModelProvider(this)[MainViewModel::class.java]
    }

    private var bins = emptyArray<String>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentMainActivityBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.bLoadData.setOnClickListener {
            viewModel.getCardInfoByBin(binding.tvBin.text.toString())
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.itemCardInfo.collect {
                    fillTextView(it)
                }
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.setBinHistory.collect {
                    bins = it.toTypedArray()
                    val arrayAdapter = ArrayAdapter(requireContext(), R.layout.item_history, bins)
                    binding.tvBin.setAdapter(arrayAdapter)
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    private fun fillTextView(cardInfo: CardInfo?) {
        if (cardInfo == null) {
            fillTextViewDefaultValues()
        } else {
            with(binding) {
                textSchemeNetwork.text = cardInfo.scheme
                textBrand.text = cardInfo.brand ?: DEFAULT_TEXT
                textLength.text = cardInfo.number.length.toString()
                textLuhn.text = if (cardInfo.number.luhn) "Yes" else "No"
                textType.text = cardInfo.type ?: DEFAULT_TEXT

                if (cardInfo.prepaid == null)
                    textPrepaid.text = DEFAULT_TEXT
                else if (cardInfo.prepaid)
                    textPrepaid.text = TEXT_YES
                else
                    textPrepaid.text = TEXT_NO

                textAlphaTwo.text = cardInfo.country?.alphaTwo ?: DEFAULT_TEXT
                textCountryName.text = cardInfo.country?.name ?: DEFAULT_TEXT

                textLatitudeLongitude.text = if (cardInfo.country?.latitude != null)
                    getString(
                        R.string.latitude_longitude,
                        cardInfo.country.latitude.toString(),
                        cardInfo.country.longitude.toString(),
                    )
                else
                    getString(
                        R.string.latitude_longitude, DEFAULT_TEXT, DEFAULT_TEXT
                    )

                textBankName.text = cardInfo.bank?.name ?: DEFAULT_TEXT
                textBankCity.text = cardInfo.bank?.city ?: DEFAULT_TEXT
                textBankUrl.text = cardInfo.bank?.url ?: DEFAULT_TEXT
                textBankPhone.text = cardInfo.bank?.phone ?: DEFAULT_TEXT
            }
        }
    }

    private fun fillTextViewDefaultValues() {
        with(binding) {
            setDefaultText(textSchemeNetwork)
            setDefaultText(textBrand)
            setDefaultText(textLength)
            setDefaultText(textLuhn)
            setDefaultText(textType)
            setDefaultText(textPrepaid)
            setDefaultText(textAlphaTwo)
            setDefaultText(textCountryName)
            setDefaultText(textLatitudeLongitude)
            setDefaultText(textBankName)
            setDefaultText(textBankCity)
            setDefaultText(textBankUrl)
            setDefaultText(textBankPhone)
        }
    }

    private fun setDefaultText(textView: TextView) {
        textView.text = DEFAULT_TEXT
    }

    companion object {
        private const val DEFAULT_TEXT = "???"
        private const val TEXT_YES = "YES"
        private const val TEXT_NO = "No"
    }
}