package br.com.fiap.trashit

import android.Manifest
import android.app.AlertDialog
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.util.Rational
import androidx.activity.ComponentActivity
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.AlertDialogDefaults
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.window.Dialog
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import br.com.fiap.trashit.ui.theme.TrashItTheme
import br.com.fiap.trashit.view.ColetasScreen
import br.com.fiap.trashit.view.LixeiraScreen
import br.com.fiap.trashit.view.LoginScreen
import br.com.fiap.trashit.view.PerfilScreen
import br.com.fiap.trashit.view.navbar.BottomNavItem
import br.com.fiap.trashit.view.navbar.BottomNavigation
import br.com.fiap.trashit.viewmodel.ColetasViewModel
import br.com.fiap.trashit.viewmodel.LixeiraViewModel
import br.com.fiap.trashit.viewmodel.LoginViewModel
import kotlin.system.exitProcess

class MainActivity : ComponentActivity() {
    private val requestPermission =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) {
            isGranted ->
            if(isGranted) Log.d("FIAPPER", "Permission Granted")
            else {
                Log.d("FIAPPER", "Not Granted")
            }
        }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
            /*if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU &&
                ContextCompat.checkSelfPermission(
                        this,
                        android.Manifest.permission.POST_NOTIFICATIONS
                ) != PackageManager.PERMISSION_GRANTED) {
                ActivityResultContracts.RequestPermission().
            }*/
        /* var end2 = Endereco(1,"09210310","125 Atualizada","Rua Atualizada", "",
            "Bairro Atualizada", "Cidade Atualizada", "Estado Atualizada", Lixeira()
        )*/

        /* Log.e("ENDERECO", enderecoRepository.atualizar(end2).toString())
        Log.e("ENDERECO", enderecoRepository.listarEnderecos().toString())
        Log.e("ENDERECO", enderecoRepository.excluir(end2).toString())
        Log.e("ENDERECO", enderecoRepository.listarEnderecos().toString())
*/
        setContent {
            TrashItTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU){
                        var permission by remember {
                            mutableStateOf<Boolean>(ContextCompat.checkSelfPermission(this,
                                Manifest.permission.POST_NOTIFICATIONS) ==
                                    PackageManager.PERMISSION_GRANTED)
                        }

                        val launcher = rememberLauncherForActivityResult(contract = ActivityResultContracts.RequestPermission()) {
                            permission = it
                        }

                        if (!permission) {
                            /*AlertDialog(
                                onDismissRequest = {
                                    this.finishAndRemoveTask()
                                    System.exit(0)},

                                confirmButton = {
                                    Button(onClick = {
                                        launcher.launch(Manifest.permission.POST_NOTIFICATIONS)

                                    }) {
                                        Text(text = "Permitir")

                                    }
                                },
                                dismissButton = {
                                    Button(onClick = {this.finishAndRemoveTask()
                                        System.exit(0)}
                                    ) {
                                        Text(text = "Cancelar")
                                    }
                                },
                                title = { Text(text = "Permissão Notificações")},
                                text = { Text(text = "O app da Trash It necessita a permissão para usar as notificações de seu dispositivo")},
                                icon = { Icon(imageVector = Icons.Default.Notifications, contentDescription = "")}
                            )*/
                            NotificationAlert(context = this, launcher = launcher)
                        }
                    }

                    val navController = rememberNavController()
                    var showBottomBar by rememberSaveable {
                        mutableStateOf(false)
                    }
                    val navBackStackEntry by navController.currentBackStackEntryAsState()

                    showBottomBar = when (navBackStackEntry?.destination?.route) {
                        "login" -> false
                        else -> true
                    }


                    Scaffold(
                        bottomBar = { if(showBottomBar) BottomNavigation(navController = navController) }) {
                        it -> Log.d("PADDING", "$it")
                        NavHost(navController = navController, startDestination = "login" ) {
                            composable(route = "login"){LoginScreen(
                                context = applicationContext,
                                viewModel = LoginViewModel(context = applicationContext),
                                navController = navController
                            )}
                            composable(route = BottomNavItem.Lixeira.screenRoute){ LixeiraScreen(
                                viewModel = LixeiraViewModel(context = applicationContext),
                                navController = navController
                            )}
                            composable(route = BottomNavItem.Coletas.screenRoute){
                                ColetasScreen(
                                    viewModel = ColetasViewModel(context = applicationContext),
                                    navController = navController
                                )
                            }
                            composable(route = BottomNavItem.Conta.screenRoute){
                                PerfilScreen(navController = navController)
                            }
                        }
                    }
                }
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Composable
fun NotificationAlert(context: MainActivity, launcher: ManagedActivityResultLauncher<String, Boolean>) {
    AlertDialog(
        onDismissRequest = {
            context.finishAndRemoveTask()
            exitProcess(0)
        },
        confirmButton = {
            Button(
                colors = ButtonDefaults.textButtonColors(containerColor = colorResource(id = R.color.TrashItGreen)) ,
                onClick = {
                    launcher.launch(Manifest.permission.POST_NOTIFICATIONS)
                }
            ) {
                Text(text = "Permitir", color = Color.White, fontWeight = FontWeight.Bold)
            }
        },
        dismissButton = {
            Button(
                colors = ButtonDefaults.textButtonColors(containerColor = colorResource(id = R.color.PlasticRed)) ,
                onClick = {
                    context.finishAndRemoveTask()
                    exitProcess(0)
                }
            ) {
                Text(text = "Cancelar", color = Color.White, fontWeight = FontWeight.Bold)
            }
        },
        title = { Text(text = "Permissão Notificações")},
        text = { Text(text = "O app da Trash It necessita a permissão para usar as notificações de seu dispositivo")},
        icon = { Icon(
            imageVector = Icons.Default.Notifications,
            contentDescription = "",
            tint = colorResource(id = R.color.TrashItGreen)
        )},
        containerColor = Color.White,

    )

}
