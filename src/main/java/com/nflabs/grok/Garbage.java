package com.nflabs.grok;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;


public class Garbage {

  private List<String> _remove;
  private Map<String, Object> _rename;

  /**
   ** Constructor
   **/
  public Garbage() {

    _remove = new ArrayList<String>();
    _rename = new TreeMap<String, Object>();
    _remove.add("UNWANTED");

  }

  /**
   * Set a map of matched field to re name
   *
   * @see rename
   * @param original name
   * @param new name
   */
  public void addToRename(String key, Object value) {
    if (key == null || value == null)
      return;
    if (!key.isEmpty() && !value.toString().isEmpty())
      _rename.put(key, value);
  }

  /**
   * Set a field list to remove from the final matched map
   *
   * @see remove
   * @param item to remove
   */
  public void addToRemove(String item) {
    if (item == null)
      return;
    if (!item.isEmpty())
      _remove.add(item);
  }

  /**
   * @see addToRemove
   * @param lst
   */
  public void addFromListRemove(List<String> lst) {
    if (lst == null)
      return;
    if (!lst.isEmpty())
      _remove.addAll(lst);
  }

  /**
   * Remove from the map the unwilling items
   *
   * @param map to clean
   * @return nb of deleted item
   */
  public int remove(Map<String, Object> map) {
    int item = 0;

    if (map == null)
      return -1;
    if (map.isEmpty())
      return -1;
    for (Iterator<Map.Entry<String, Object>> it = map.entrySet().iterator(); it.hasNext();) {
      Map.Entry<String, Object> entry = it.next();
      for (int i = 0; i < _remove.size(); i++)
        if (entry.getKey().equals(_remove.get(i))) {
          it.remove();
          item++;
        }
    }
    return item;
  }

  /**
   * @see addToRename
   * @param map
   * @return nb of renamed items
   */
  public int rename(Map<String, Object> map) {
    int item = 0;

    if (map == null)
      return -1;
    if (map.isEmpty() || _rename.isEmpty())
      return -1;

    for (Iterator<Map.Entry<String, Object>> it = _rename.entrySet().iterator(); it.hasNext();) {
      Map.Entry<String, Object> entry = it.next();
      if (map.containsKey(entry.getKey())) {
        Object obj = map.remove(entry.getKey());
        map.put(entry.getValue().toString(), obj);
        item++;
      }
    }

    return item;
  }

}
