package br.com.fiap.trashit.view.navbar

import br.com.fiap.trashit.R

sealed class BottomNavItem(
    var title: String,
    var icon: Int,
    var screenRoute: String,
) {
    object Lixeira : BottomNavItem(
        "Lixeira",
        R.drawable.baseline_delete_24,
        "lixeira"
    )
    object Coletas : BottomNavItem(
        "Coletas",
        R.drawable.baseline_checklist_rtl_24,
        "coletas"
    )
    object Conta : BottomNavItem(
        "Perfil",
        R.drawable.baseline_person_2_24,
        "Perfil"
    )
}
