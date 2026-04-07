package com.example.cars

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController

class ProductFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_product, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val cars = listOf(
            Triple("BMW M3 (F80 generation)", 38000f, R.drawable.car1),
            Triple("Mercedes-Benz CLA-Class (Second Generation)", 46400f, R.drawable.car2),
            Triple("Porsche 911 GT3 RS (991.1 Generation)", 189000f, R.drawable.car3),
            Triple("Ferrari 488 Spider", 260000f, R.drawable.car4)
        )

        val layouts = listOf(
            view.findViewById<LinearLayout>(R.id.car1Layout),
            view.findViewById<LinearLayout>(R.id.car2Layout),
            view.findViewById<LinearLayout>(R.id.car3Layout),
            view.findViewById<LinearLayout>(R.id.car4Layout)
        )

        layouts.forEachIndexed { index, layout ->
            layout.setOnClickListener {
                val bundle = Bundle()
                bundle.putString("carName", cars[index].first)
                bundle.putFloat("carPrice", cars[index].second)
                bundle.putInt("carImage", cars[index].third)
                findNavController().navigate(R.id.action_product_to_checkout, bundle)
            }
        }
    }
}