import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'home',
      component: HomeView
    },
    {
      path: '/historique/:year',
      name: 'historique',
      // route level code-splitting
      // this generates a separate chunk (About.[hash].js) for this route
      // which is lazy-loaded when the route is visited.
      component: () => import('../views/GalerieView.vue')
    },

    {
      path: '/galerie/:year',
      name: 'galerie',
      // route level code-splitting
      // this generates a separate chunk (About.[hash].js) for this route
      // which is lazy-loaded when the route is visited.
      component: () => import('../views/GalerieView.vue')
    },

    {
      path: '/admin/:year',
      name: 'admin',
      // route level code-splitting
      // this generates a separate chunk (About.[hash].js) for this route
      // which is lazy-loaded when the route is visited.
      component: () => import('../views/admin/HomeView.vue')
    },

    {
      path: '/connexion',
      name: 'connexion',
      // route level code-splitting
      // this generates a separate chunk (About.[hash].js) for this route
      // which is lazy-loaded when the route is visited.
      component: () => import('../views/ConnexionView.vue')
    },

    {
      path: '/poules',
      name: 'poules',
      // route level code-splitting
      // this generates a separate chunk (About.[hash].js) for this route
      // which is lazy-loaded when the route is visited.
      component: () => import('../views/PouleView.vue')
      },

      {
          path: '/admin/poules/:year',
          name: 'admin_poule',
          // route level code-splitting
          // this generates a separate chunk (About.[hash].js) for this route
          // which is lazy-loaded when the route is visited.
          component: () => import('../views/admin/PouleView.vue')
      },

    {
      path: '/tableaux/:rang',
      name: 'tableaux',
      // route level code-splitting
      // this generates a separate chunk (About.[hash].js) for this route
      // which is lazy-loaded when the route is visited.
      component: () => import('../views/TableauView.vue')
      },

   
      
    {
      path: '/equipes',
      name: 'equipes',
      // route level code-splitting
      // this generates a separate chunk (About.[hash].js) for this route
      // which is lazy-loaded when the route is visited.
      component: () => import('../views/TeamView.vue')
    },

    {
        path: '/admin/equipe/:year',
        name: 'equipe_admin',
        // route level code-splitting
        // this generates a separate chunk (About.[hash].js) for this route
        // which is lazy-loaded when the route is visited.
        component: () => import('../views/admin/EquipeView.vue')
    },

    {
      path: '/inscrire_equipe',
      name: 'inscrire_equipe',
      // route level code-splitting
      // this generates a separate chunk (About.[hash].js) for this route
      // which is lazy-loaded when the route is visited.
      component: () => import('../views/CreateTeamView.vue')
    },


      {
        path: '/admin/tableaux/:year',
        name: 'tableaux_admin',
        // route level code-splitting
        // this generates a separate chunk (About.[hash].js) for this route
        // which is lazy-loaded when the route is visited.
        component: () => import('../views/admin/TableauView.vue')
    },

    {
        path: '/admin/equipe/:year',
        name: 'equipe_admin',
        // route level code-splitting
        // this generates a separate chunk (About.[hash].js) for this route
        // which is lazy-loaded when the route is visited.
        component: () => import('../views/admin/EquipeView.vue')
      },

    {
        path: '/admin/accueil',
        name: 'accueil',
        // route level code-splitting
        // this generates a separate chunk (About.[hash].js) for this route
        // which is lazy-loaded when the route is visited.
        component: () => import('../views/admin/PouleView.vue')
      },


      {
          path: '/admin/information_utile',
          name: 'admin_information_utile',
          // route level code-splitting
          // this generates a separate chunk (About.[hash].js) for this route
          // which is lazy-loaded when the route is visited.
          component: () => import('../views/admin/InformationUtile.vue')
      },

      {
          path: '/admin/photo/:year',
          name: 'photo_admin',
          // route level code-splitting
          // this generates a separate chunk (About.[hash].js) for this route
          // which is lazy-loaded when the route is visited.
          component: () => import('../views/admin/Photo.vue')
      },

  ]
})

export default router
