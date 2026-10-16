package com.example.tugas5

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectable
import androidx.compose.material3.Button
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.dp

@Composable
fun FormDataDiri(modifier: Modifier = Modifier) {

    // Input state variables
    var textNama by remember { mutableStateOf("") }
    var textAlamat by remember { mutableStateOf("") }
    var textJK by remember { mutableStateOf("") }

    // Submitted state variables
    var namaSubmitted by remember { mutableStateOf("") }
    var alamatSubmitted by remember { mutableStateOf("") }
    var jenisSubmitted by remember { mutableStateOf("") }

    val genderList = listOf("Laki-laki", "Perempuan")

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        OutlinedTextField(
            value = textNama,
            onValueChange = { textNama = it },
            singleLine = true,
            shape = MaterialTheme.shapes.large,
            modifier = Modifier.fillMaxWidth(),
            label = { Text("Nama Lengkap") }
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Radio Buttons for Gender Selection
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Start
        ) {
            genderList.forEach { item ->
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .selectable(
                            selected = (textJK == item),
                            onClick = { textJK = item }
                        )
                        .padding(horizontal = 8.dp)
                ) {
                    RadioButton(
                        selected = (textJK == item),
                        onClick = { textJK = item }
                    )
                    Text(text = item)
                }
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = textAlamat,
            onValueChange = { textAlamat = it },
            singleLine = true,
            shape = MaterialTheme.shapes.large,
            modifier = Modifier.fillMaxWidth(),
            label = { Text("Alamat") }
        )

        HorizontalDivider(
            modifier = Modifier.padding(vertical = dimensionResource(id = R.dimen.padding_medium)),
            thickness = dimensionResource(id = R.dimen.diveder_tipis)
        )

        Button(
            modifier = Modifier.fillMaxWidth(),
            enabled = textNama.isNotBlank() && textAlamat.isNotBlank() && textJK.isNotEmpty(),
            onClick = {
                namaSubmitted = textNama
                jenisSubmitted = textJK
                alamatSubmitted = textAlamat
            }
        ) {
            Text(text = "Submit")
        }

        // Output Card displaying submitted data
        if (namaSubmitted.isNotEmpty()) {
            Spacer(modifier = Modifier.height(16.dp))

            ElevatedCard(
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = "Data Diri Terdaftar",
                        style = MaterialTheme.typography.titleMedium
                    )
                    HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))
                    Text(text = "Nama: $namaSubmitted")
                    Text(text = "Jenis Kelamin: $jenisSubmitted")
                    Text(text = "Alamat: $alamatSubmitted")
                }
            }
        }
    }
}