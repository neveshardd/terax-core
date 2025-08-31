package world.terax.servers.balancer;

import world.terax.servers.balancer.elements.LoadBalancerObject;

public interface LoadBalancer<T extends LoadBalancerObject> {
  T next();
}
