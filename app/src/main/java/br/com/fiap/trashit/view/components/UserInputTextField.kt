package br.com.fiap.trashit.view.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import br.com.fiap.trashit.R

@Composable
fun UserInputTextField(
    text: String,
    value: String,
    onCheckedFunction: (it: String) -> Unit,
    visualTransformation: VisualTransformation,
    keyboardOptions: KeyboardOptions = KeyboardOptions(),
    enabled: Boolean = false,
    modifier: Modifier = Modifier.fillMaxWidth().padding(horizontal = 40.dp)
) {
    OutlinedTextField(
        label = { Text(text = text, color = Color.White) },
        value = value,
        onValueChange = { onCheckedFunction(it) },
        visualTransformation = visualTransformation,
        keyboardOptions = keyboardOptions,
        enabled = enabled,
        colors = OutlinedTextFieldDefaults.colors(
            unfocusedBorderColor = Color.White,
            focusedBorderColor = colorResource(id = R.color.trashIt_green),
            cursorColor = colorResource(id = R.color.trashIt_green),
            unfocusedTextColor = Color.White,
            focusedTextColor = Color.White,
            disabledBorderColor = Color.DarkGray,
            disabledTextColor = Color.LightGray,
        ),
        modifier = modifier
    )
}