import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'


const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'home',
      component: HomeView,
      meta: {
        allowAnonymous: true
      }
    },
    {
      path: '/historiques/:year',
      name: 'historique',
      // route level code-splitting
      // this generates a separate chunk (About.[hash].js) for this route
      // which is lazy-loaded when the route is visited.
      component: () => import('../views/historique/ResumeView.vue'),
      meta: {
        allowAnonymous: true
      }
    },
    {
      path: '/historiques/:year/poules',
      name: 'historique_poules',
      // route level code-splitting
      // this generates a separate chunk (About.[hash].js) for this route
      // which is lazy-loaded when the route is visited.
      component: () => import('../views/historique/PouleView.vue'),
      meta: {
        allowAnonymous: true
      }
    },

    {
      path: '/historiques/:year/tableaux/:rang',
      name: 'historique_tableaux',
      // route level code-splitting
      // this generates a separate chunk (About.[hash].js) for this route
      // which is lazy-loaded when the route is visited.
      component: () => import('../views/historique/TableauView.vue'),
      meta: {
        allowAnonymous: true
      }
    },

    {
      path: '/historiques/:year/equipes',
      name: 'historique_equipes',
      // route level code-splitting
      // this generates a separate chunk (About.[hash].js) for this route
      // which is lazy-loaded when the route is visited.
      component: () => import('../views/historique/TeamView.vue'),
      meta: {
        allowAnonymous: true
      }
    },

    {
      path: '/galerie/:year',
      name: 'galerie',
      // route level code-splitting
      // this generates a separate chunk (About.[hash].js) for this route
      // which is lazy-loaded when the route is visited.
      component: () => import('../views/GalerieView.vue'),
      meta: {
        allowAnonymous: true
      }
    },

    {
      path: '/admin/:year',
      name: 'admin',
      // route level code-splitting
      // this generates a separate chunk (About.[hash].js) for this route
      // which is lazy-loaded when the route is visited.
      component: () => import('../views/admin/HomeView.vue'),
      meta: {
        allowAnonymous: false
      }
    },

    {
      path: '/connexion',
      name: 'connexion',
      // route level code-splitting
      // this generates a separate chunk (About.[hash].js) for this route
      // which is lazy-loaded when the route is visited.
      component: () => import('../views/ConnexionView.vue'),
      meta: {
        allowAnonymous: true
      }
    },

    {
      path: '/poules',
      name: 'poules',
      // route level code-splitting
      // this generates a separate chunk (About.[hash].js) for this route
      // which is lazy-loaded when the route is visited.
      component: () => import('../views/PouleView.vue'),
      meta: {
        allowAnonymous: true
      }
    },


    {
      path: '/tableaux/:rang',
        name: 'tableaux',
        // route level code-splitting
        // this generates a separate chunk (About.[hash].js) for this route
        // which is lazy-loaded when the route is visited.
        component: () => import('../views/TableauView.vue'),
        meta: {
          allowAnonymous: true
        }
      },

  
      
    {
      path: '/equipes',
      name: 'equipes',
      // route level code-splitting
      // this generates a separate chunk (About.[hash].js) for this route
      // which is lazy-loaded when the route is visited.
      component: () => import('../views/TeamView.vue'),
      meta: {
        allowAnonymous: true
      }
    },

    {
      path: '/inscrire_equipe',
      name: 'inscrire_equipe',
      // route level code-splitting
      // this generates a separate chunk (About.[hash].js) for this route
      // which is lazy-loaded when the route is visited.
      component: () => import('../views/CreateTeamView.vue'),
      meta: {
        allowAnonymous: true
      }
    },

    {
        path: '/admin/accueil/:year',
        name: 'accueil',
        // route level code-splitting
        // this generates a separate chunk (About.[hash].js) for this route
        // which is lazy-loaded when the route is visited.
        component: () => import('../views/admin/HomeView.vue'),
        meta: {
          allowAnonymous: false
        }
      },

      {
        path: '/admin/creer_tournoi',
        name: 'creer_tournoi',
        // route level code-splitting
        // this generates a separate chunk (About.[hash].js) for this route
        // which is lazy-loaded when the route is visited.
        component: () => import('../views/admin/CreateTournoiView.vue'),
        meta: {
          allowAnonymous: false
        }
      },


      {
          path: '/admin/information_utile',
          name: 'admin_information_utile',
          // route level code-splitting
          // this generates a separate chunk (About.[hash].js) for this route
          // which is lazy-loaded when the route is visited.
          component: () => import('../views/admin/InformationUtile.vue'),
          meta: {
            allowAnonymous: false
          }
      },

      {
          path: '/admin/photo/:year',
          name: 'photo_admin',
          // route level code-splitting
          // this generates a separate chunk (About.[hash].js) for this route
          // which is lazy-loaded when the route is visited.
          component: () => import('../views/admin/Photo.vue'),
          meta: {
            allowAnonymous: false
          }
      },

  ]
});


router.beforeEach((to, from, next) => {
  const is_admin = localStorage.getItem('is_admin')?Boolean(localStorage.getItem('is_admin')):false
  if (to.name == 'connexion' && is_admin) {
    next({ path: '/admin/accueil/'+new Date().getFullYear() })
  }
  else if (!to.meta.allowAnonymous && !is_admin) {
      next({
          path: '/connexion',
          query: { redirect: to.fullPath }
      })
  }
  else if (to.meta.allowAnonymous && is_admin) {
    next({ path: '/admin/accueil/'+new Date().getFullYear() })

  }
  else {
      next()
  }
})

export default router
