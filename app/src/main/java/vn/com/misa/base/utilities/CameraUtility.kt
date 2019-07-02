package vn.com.misa.base.utilities

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.widget.ImageView
import androidx.fragment.app.Fragment
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver
import vn.com.base.libraries.utilities.FileUtility
import vn.com.base.libraries.utilities.MediaUtility
import vn.com.base.libraries.utilities.OptionalUtility
import vn.com.base.libraries.utilities.StringUtility
import vn.com.base.libraries.views.keypairs.ExtKeyPair
import vn.com.misa.base.R
import vn.com.misa.base.commons.activities.BaseAppCompatActivity
import java.io.File

class CameraUtility {
    companion object {
        fun getCameraOrGallery(context: Context): ArrayList<ExtKeyPair?>? {
            with(ArrayList<ExtKeyPair?>()) {
                add(ExtKeyPair(context.getString(R.string.camera), context.getString(R.string.camera)))
                add(ExtKeyPair(context.getString(R.string.gallery), context.getString(R.string.gallery)))
                return this
            }
        }

        /**
         * @param compositeDisposable
         * @param mActivity
         * @param fragment
         * @param filePath MediaUtility.getImageUrlPng();
         */
        fun actionImageCameraOrGallery(compositeDisposable: CompositeDisposable, mActivity: BaseAppCompatActivity<*, *>,
                                       fragment: Fragment, filePath: File) {
            if (OptionalUtility.isNullOrEmpty(fragment.fragmentManager)) return

            compositeDisposable.add(mActivity.rxPermissions
                    .request(Manifest.permission.CAMERA,
                            Manifest.permission.READ_EXTERNAL_STORAGE,
                            Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeWith(object : DisposableObserver<Boolean>() {
                        override fun onComplete() {}
                        override fun onNext(granted: Boolean) {
                            if (!granted) return
//                            ExtKeyPairDialogFragment.newInstance()
//                                    .setExtKeyValuePairs(getCameraOrGallery(mActivity))
//                                    .setOnSelectedConsumer {
//                                        if (it.key.equals(mActivity.getString(R.string.camera), true)) {
//                                            MediaUtility.captureImage(fragment, filePath)
//                                        }
//
//                                        if (it.key.equals(mActivity.getString(R.string.gallery), true)) {
//                                            MediaUtility.chooseImageFromGallery(fragment, mActivity.getString(R.string.select_value))
//                                        }
//                                    }
//                                    .show(fragment.fragmentManager!!, null)
                        }

                        override fun onError(e: Throwable) {}
                    }))
        }

        /**
         * @param mActivity
         * @param requestCode
         * @param resultCode
         * @param data
         * @param filePath
         * @param ivImage
         * @param fileConsumer(filePath)
         */
        fun actionImageCameraOrGalleryOnActivityResult(mActivity: Activity, requestCode: Int, resultCode: Int,
                                                       data: Intent?, filePath: File?, ivImage: ImageView?,
                                                       fileConsumer: ((File) -> Unit)? = null) {
            if (resultCode != Activity.RESULT_OK) return

            when (requestCode) {
                MediaUtility.CAMERA_IMAGE_REQUEST -> {
                    // display image
                    OptionalUtility.with(ivImage).doIfPresent {
                        PicasoUtility.get()
                                .load(filePath!!)
                                .placeholder(R.drawable.bg_background_radius_component)
                                .into(it)
                    }
                    // consumer file
                    OptionalUtility.with(fileConsumer).doIfPresent { it(filePath!!) }
                }
                MediaUtility.REQUEST_PICK_IMAGE -> {
                    val pathFile = MediaUtility.getPath(mActivity, data?.data)
                    if (StringUtility.isNullOrEmpty(pathFile)) return

                    FileUtility.with(FileUtility.getFileFromPath(pathFile)).doIfPresenter { f ->
                        // display image
                        OptionalUtility.with(ivImage).doIfPresent {
                            PicasoUtility.get()
                                    .load(f)
                                    .placeholder(R.drawable.bg_background_radius_component)
                                    .into(it)
                        }

                        // consumer file
                        OptionalUtility.with(fileConsumer).doIfPresent { c -> c(f) }
                    }
                }
            }
        }

        /**
         * @param compositeDisposable
         * @param mActivity
         * @param fragment
         * @param filePath MediaUtility.getVideoNameMp4();
         */
        fun actionVideoCameraOrGallery(compositeDisposable: CompositeDisposable, mActivity: BaseAppCompatActivity<*, *>,
                                       fragment: Fragment, filePath: File) {
            if (OptionalUtility.isNullOrEmpty(fragment.fragmentManager)) return

            compositeDisposable.add(mActivity.rxPermissions
                    .request(Manifest.permission.CAMERA,
                            Manifest.permission.READ_EXTERNAL_STORAGE,
                            Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeWith(object : DisposableObserver<Boolean>() {
                        override fun onComplete() {}
                        override fun onNext(granted: Boolean) {
                            if (!granted) return
//                            ExtKeyPairDialogFragment.newInstance()
//                                    .setExtKeyValuePairs(getCameraOrGallery(mActivity))
//                                    .setOnSelectedConsumer {
//                                        if (it.key.equals(mActivity.getString(R.string.camera), true)) {
//                                            MediaUtility.captureVideo(fragment, filePath)
//                                        }
//
//                                        if (it.key.equals(mActivity.getString(R.string.gallery), true)) {
//                                            MediaUtility.chooseVideoFromGallery(fragment, mActivity.getString(R.string.select_value))
//                                        }
//                                    }
//                                    .show(fragment.fragmentManager!!, null)
                        }

                        override fun onError(e: Throwable) {}
                    }))
        }

        /**
         * @param mActivity
         * @param requestCode
         * @param resultCode
         * @param data
         * @param filePath
         * @param fileConsumer(filePath)
         */
        fun actionVideoCameraOrGalleryOnActivityResult(mActivity: Activity, requestCode: Int, resultCode: Int,
                                                       data: Intent?, filePath: File?, fileConsumer: ((File) -> Unit)? = null) {
            if (resultCode != Activity.RESULT_OK) return

            when (requestCode) {
                MediaUtility.CAMERA_VIDEO_REQUEST -> {
                    OptionalUtility.with(fileConsumer).doIfPresent { it(filePath!!) }
                }
                MediaUtility.REQUEST_PICK_VIDEO -> {
                    val pathFile = MediaUtility.getPath(mActivity, data?.data)
                    if (StringUtility.isNullOrEmpty(pathFile)) return

                    FileUtility.with(FileUtility.getFileFromPath(pathFile)).doIfPresenter { f ->
                        OptionalUtility.with(fileConsumer).doIfPresent { c -> c(f) }
                    }
                }
            }
        }
    }
}