package com.zcwfeng.componentlibs.surport.inject2;

//public class InjectUtility {
//	private static final String METHOD_SET_CONTENTVIEW = "setContentView";
//	private static final String METHOD_FIND_VIEW_BY_ID = "findViewById";
//
//
//
//	public static void inject(Activity activity)
//	{
//
//		injectContentView(activity);
//		injectViews(activity);
//
//	}
//
//	/**
//	 * 注入主布局文件
//	 *
//	 * @param activity
//	 */
//	private static void injectContentView(Activity activity)
//	{
//		Class<? extends Activity> clazz = activity.getClass();
//		// 查询类上是否存在ContentView注解
//		ContentView contentView = clazz.getAnnotation(ContentView.class);
//		if (contentView != null)// 存在
//		{
//			int contentViewLayoutId = contentView.value();
//			try
//			{
//				Method method = clazz.getMethod(METHOD_SET_CONTENTVIEW,
//						int.class);
//				method.setAccessible(true);
//				method.invoke(activity, contentViewLayoutId);
//			} catch (Exception e)
//			{
//				e.printStackTrace();
//			}
//		}
//	}
//
//
//
//
//	/**
//	 * 注入所有的控件
//	 *
//	 * @param activity
//	 */
//	private static void injectViews(Activity activity)
//	{
//		Class<? extends Activity> clazz = activity.getClass();
//		Field[] fields = clazz.getDeclaredFields();
//		// 遍历所有成员变量
//		for (Field field : fields)
//		{
//
//			ViewInject viewInjectAnnotation = field
//					.getAnnotation(ViewInject.class);
//			if (viewInjectAnnotation != null)
//			{
//				int viewId = viewInjectAnnotation.value();
//				if (viewId != -1)
//				{
//					Log.e("TAG", viewId+"");
//					// 初始化View
//					try
//					{
//						Method method = clazz.getMethod(METHOD_FIND_VIEW_BY_ID,
//								int.class);
//						Object resView = method.invoke(activity, viewId);
//						field.setAccessible(true);
//						field.set(activity, resView);
//					} catch (Exception e)
//					{
//						e.printStackTrace();
//					}
//
//				}
//			}
//
//		}
//
//	}
//
//
//	/**
//	 * 注入所有的事件
//	 *
//	 * @param activity
//	 */
//	private static void injectEvents(Activity activity)
//	{
//
//		Class<? extends Activity> clazz = activity.getClass();
//		Method[] methods = clazz.getMethods();
//		//遍历所有的方法
//		for (Method method : methods)
//		{
//			Annotation[] annotations = method.getAnnotations();
//			//拿到方法上的所有的注解
//			for (Annotation annotation : annotations)
//			{
//				Class<? extends Annotation> annotationType = annotation
//						.annotationType();
//				//拿到注解上的注解
//				EventBase eventBaseAnnotation = annotationType
//						.getAnnotation(EventBase.class);
//				//如果设置为EventBase
//				if (eventBaseAnnotation != null)
//				{
//					//取出设置监听器的名称，监听器的类型，调用的方法名
//					String listenerSetter = eventBaseAnnotation
//							.listenerSetter();
//					Class<?> listenerType = eventBaseAnnotation.listenerType();
//					String methodName = eventBaseAnnotation.methodName();
//
//					try
//					{
//						//拿到Onclick注解中的value方法
//						Method aMethod = annotationType
//								.getDeclaredMethod("value");
//						//取出所有的viewId
//						int[] viewIds = (int[]) aMethod
//								.invoke(annotation);
//						//通过InvocationHandler设置代理
//						DynamicHandler handler = new DynamicHandler(activity);
//						handler.addMethod(methodName, method);
//						Object listener = Proxy.newProxyInstance(
//								listenerType.getClassLoader(),
//								new Class<?>[] { listenerType }, handler);
//						//遍历所有的View，设置事件
//						for (int viewId : viewIds)
//						{
//							View view = activity.findViewById(viewId);
//							Method setEventListenerMethod = view.getClass()
//									.getMethod(listenerSetter, listenerType);
//							setEventListenerMethod.invoke(view, listener);
//						}
//
//					} catch (Exception e)
//					{
//						e.printStackTrace();
//					}
//				}
//
//			}
//		}
//
//	}
//
//}
