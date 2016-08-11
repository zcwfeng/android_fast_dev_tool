package com.zcwfeng.componentlibs.surport.inject;

//
//public class InjectUtility {
//	private static final String METHOD_SET_CONTENTVIEW = "setContentView";
////	private static final String METHOD_FIND_VIEW_BY_ID = "findViewById";
//
//	public static void initInjectedView(Activity sourceActivity) {
//		initInjectedView(sourceActivity, sourceActivity.getWindow().getDecorView());
//	}
//
//
//	public static void inject(Activity activity)
//	{
//
//		injectContentView(activity);
//		initInjectedView(activity);
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
//						Integer.class);
//				method.setAccessible(true);
//				method.invoke(activity, contentViewLayoutId);
//			} catch (Exception e)
//			{
//				e.printStackTrace();
//			}
//		}
//	}
//
//    public static void initInjectedView(Object injectedSource, View sourceView) {
//		Class<?> clazz = injectedSource.getClass();
//
//
//		for (; clazz != Object.class; clazz = clazz.getSuperclass()) {
//			Field[] fields = clazz.getDeclaredFields();
//			if (fields != null && fields.length > 0) {
//				for (Field field : fields) {
//					ViewInject viewInject = field.getAnnotation(ViewInject.class);
//					if (viewInject != null) {
//						// ViewId可以是id配置，也可以是IdStr配置
//						int viewId = viewInject.id();
//						if (viewId == 0) {
//							String idStr = viewInject.idStr();
//							if (!TextUtils.isEmpty(idStr)) {
//								try {
//									Context context = BaseApplication.getInstance();
//									String packageName = context.getPackageName();
//									Resources resources = context.getPackageManager().getResourcesForApplication(packageName);
//
//									viewId = resources.getIdentifier(idStr, "id", packageName);
//
//									if (viewId == 0)
//										throw new RuntimeException(String.format("%s 的属性%s关联了id=%s，但是这个id是无效的", clazz.getSimpleName(),
//												field.getName(), idStr));
//								} catch (Exception e) {
////									e.printStackTrace();
//								}
//							}
//						}
//						if (viewId != 0) {
//							try {
//								field.setAccessible(true);
//								/*
//								 * 当已经被赋值时，不在重复赋值，用于include，inflate情景下的viewinject组合
//								 */
//								if (field.get(injectedSource) == null) {
//									field.set(injectedSource, sourceView.findViewById(viewId));
//								} else {
//									continue;
//								}
//							} catch (Exception e) {
////								e.printStackTrace();
//							}
//						}
//
//						String clickMethod = viewInject.click();
//						if (!TextUtils.isEmpty(clickMethod))
//							setViewClickListener(injectedSource, field, clickMethod);
//
//						String longClickMethod = viewInject.longClick();
//						if (!TextUtils.isEmpty(longClickMethod))
//							setViewLongClickListener(injectedSource, field, longClickMethod);
//
//						String itemClickMethod = viewInject.itemClick();
//						if (!TextUtils.isEmpty(itemClickMethod))
//							setItemClickListener(injectedSource, field, itemClickMethod);
//
//						String itemLongClickMethod = viewInject.itemLongClick();
//						if (!TextUtils.isEmpty(itemLongClickMethod))
//							setItemLongClickListener(injectedSource, field, itemLongClickMethod);
//
////						Select select = viewInject.select();
////						if (!TextUtils.isEmpty(select.selected()))
////							setViewSelectListener(injectedSource, field, select.selected(), select.noSelected());
//
//					}
//				}
//			}
//		}
//	}
//
//    public static void setViewClickListener(Object injectedSource, Field field, String clickMethod) {
//		try {
//			Object obj = field.get(injectedSource);
//			if (obj instanceof View) {
//				((View) obj).setOnClickListener(new EventListener(injectedSource).click(clickMethod));
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//
//    public static void setViewLongClickListener(Object injectedSource, Field field, String clickMethod) {
//		try {
//			Object obj = field.get(injectedSource);
//			if (obj instanceof View) {
//				((View) obj).setOnLongClickListener(new EventListener(injectedSource).longClick(clickMethod));
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//
//    public static void setItemClickListener(Object injectedSource, Field field, String itemClickMethod) {
//		try {
//			Object obj = field.get(injectedSource);
//			if (obj instanceof AbsListView) {
//				((AbsListView) obj).setOnItemClickListener(new EventListener(injectedSource).itemClick(itemClickMethod));
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//
//    public static void setItemLongClickListener(Object injectedSource, Field field, String itemClickMethod) {
//		try {
//			Object obj = field.get(injectedSource);
//			if (obj instanceof AbsListView) {
//				((AbsListView) obj).setOnItemLongClickListener(new EventListener(injectedSource).itemLongClick(itemClickMethod));
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//
//    public static void setViewSelectListener(Object injectedSource, Field field, String select, String noSelect) {
//		try {
//			Object obj = field.get(injectedSource);
//			if (obj instanceof View) {
//				((AbsListView) obj).setOnItemSelectedListener(new EventListener(injectedSource).select(select).noSelect(noSelect));
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//
//}
