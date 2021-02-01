package com.jc.platform.model;

import com.jc.platform.boot.launch.PlatformApplication;
import com.jc.platform.cloud.annotation.PlatformCloudApplication;

/**
 * ClassName PlatformModelApplication
 * Description 
 *
 * @author 平台管理员
 * @version 4.0
 * @date 2021/01/28
 */
@PlatformCloudApplication
public class PlatformModelApplication
{
	public static void main(String[] args)
	{
        PlatformApplication.run("platform-model", "platform-model", PlatformModelApplication.class, args);
	}
}
