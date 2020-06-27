package pers.zander.kotlinmvvm.practice.model.bean

import java.io.Serializable

/**
 * Created by Zander on 2020/6/26.
 * Author:Zander
 * Mail:zander.zhang2018@gmail.com
 * Depiction:
 */
data class SystemChild(val child: List<SystemChild>,
                  val courseId: Int,
                  val id: Int,
                  val name: String,
                  val order: Int,
                  val parentChapterId: Int,
                  val visible: Int): Serializable