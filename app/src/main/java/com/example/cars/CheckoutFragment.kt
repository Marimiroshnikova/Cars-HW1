package com.example.cars

import android.graphics.Paint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController

class CheckoutFragment : Fragment() {

    private var discountedPrice: Double = 0.0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_checkout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val carName = arguments?.getString("carName") ?: ""
        val carImage = arguments?.getInt("carImage") ?: R.drawable.car1
        val originalPrice = arguments?.getFloat("carPrice")?.toDouble() ?: 0.0

        discountedPrice = originalPrice * 0.95

        val tvName = view.findViewById<TextView>(R.id.tvCarName)
        val ivImage = view.findViewById<ImageView>(R.id.ivCarThumb)
        val tvOriginal = view.findViewById<TextView>(R.id.tvOriginalPrice)
        val tvDiscounted = view.findViewById<TextView>(R.id.tvDiscountedPrice)
        val tvTotal = view.findViewById<TextView>(R.id.tvTotal)

        val imgStandard = view.findViewById<ImageView>(R.id.imgRadioStandard)
        val imgExpress = view.findViewById<ImageView>(R.id.imgRadioExpress)

        val layoutStandard = view.findViewById<View>(R.id.layoutStandard)
        val layoutExpress = view.findViewById<View>(R.id.layoutExpress)

        val btnPay = view.findViewById<Button>(R.id.btnPay)

        tvName.text = carName
        ivImage.setImageResource(carImage)

        tvOriginal.text = "$%.2f".format(originalPrice)
        tvOriginal.paintFlags = tvOriginal.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG

        tvDiscounted.text = "$%.2f".format(discountedPrice)

        setStandard(imgStandard, imgExpress, tvTotal)

        layoutStandard.setOnClickListener {
            setStandard(imgStandard, imgExpress, tvTotal)
        }

        layoutExpress.setOnClickListener {
            setExpress(imgStandard, imgExpress, tvTotal)
        }

        btnPay.setOnClickListener {
            findNavController().navigate(R.id.action_checkout_to_confirmation)
        }
    }

    private fun setStandard(
        imgStandard: ImageView,
        imgExpress: ImageView,
        tvTotal: TextView
    ) {
        imgStandard.setImageResource(R.drawable.ic_radio_on)
        imgExpress.setImageResource(R.drawable.ic_radio_off)
        tvTotal.text = "$%.2f".format(discountedPrice)
    }

    private fun setExpress(
        imgStandard: ImageView,
        imgExpress: ImageView,
        tvTotal: TextView
    ) {
        imgStandard.setImageResource(R.drawable.ic_radio_off)
        imgExpress.setImageResource(R.drawable.ic_radio_on)
        tvTotal.text = "$%.2f".format(discountedPrice + 1700.0)
    }
}