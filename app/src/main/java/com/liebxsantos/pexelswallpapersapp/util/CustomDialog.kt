package com.liebxsantos.pexelswallpapersapp.util

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.liebxsantos.core.domain.model.PhotoDomain
import com.liebxsantos.pexelswallpapersapp.databinding.CustomDialogFragmentBinding
import com.liebxsantos.pexelswallpapersapp.ui.extensions.loadBlurredImageWithPlaceholder

class CustomDialog(
    private val photoDomain: PhotoDomain,
    private val clickListener: () -> Unit
): DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val binding = CustomDialogFragmentBinding.inflate(layoutInflater)

        binding.btnNo.setOnClickListener {
            dismiss()
        }

        binding.btnYes.setOnClickListener {
            clickListener.invoke()
            dismiss()
        }

        binding.image.loadBlurredImageWithPlaceholder(photoDomain.srcDomain?.small, photoDomain.avgColor)

        return MaterialAlertDialogBuilder(
            requireContext(),
            com.google.android.material.R.style.MaterialAlertDialog_Material3
        )
            .setCancelable(false)
            .setView(binding.root)
            .create()
            .apply {
                window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            }
    }
}