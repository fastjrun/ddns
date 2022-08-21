package com.fastjrun.ddns.service;

import com.fastjrun.ddns.config.AppBean;

public interface CacheService {

  AppBean cache();

  void refresh();
}
