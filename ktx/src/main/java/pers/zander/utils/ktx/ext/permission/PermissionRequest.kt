package pers.zander.utils.ktx.ext.permission

/**
 * Created by zander
 * on 2019/6/21 15:28
 */
data class PermissionRequest(
    val permissionFragment: KtxPermissionFragment,
    val permissions: List<String>,
    val requestCode: Int
) {

    fun retry() {
        permissionFragment.requestPermissionsByFragment(permissions.toTypedArray(), requestCode)
    }
}